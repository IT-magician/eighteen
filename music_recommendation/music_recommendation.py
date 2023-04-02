import os
import shutil

import pandas as pd
from sklearn.preprocessing import MinMaxScaler
from sklearn.cluster import KMeans
import json
from pandas import json_normalize
import numpy as np
from sklearn.tree import DecisionTreeClassifier

from surprise import SVD
from surprise import Reader, Dataset


DATA_DIR = "../data"
DATA_FILE = os.path.join(DATA_DIR, "results.json")
DATA_FILE2 = os.path.join(DATA_DIR, "emotions.json")
DATA_FILE3 = os.path.join(DATA_DIR, "favorite_musics.json")

music_columns = (
    "id",
    "title",
    "singer",
    "thumbnail_url", 
    "popularity", 
    "danceability",
    "energy",
    "valence",
    "key",
    "tempo"
)

def kmeans_clustering(data_path=DATA_FILE):
    with open(data_path, encoding="utf-8") as f:
        data = json.loads(f.read())

    df = pd.DataFrame(data)
    df = df.replace('', np.NaN)
    df = df.dropna(axis=0)

    mood_df = df[['danceability', 'energy', 'valence']]

    scaler = MinMaxScaler()
    mood_df_scaled = scaler.fit_transform(mood_df)

    k = 3
    kmeans = KMeans(n_clusters = k, random_state = 10)
    kmeans.fit(mood_df_scaled)
    df['cluster'] = kmeans.fit_predict(mood_df_scaled)

    data = {'danceability': 0.8, 'energy': 0.6, 'valence': 0.9}
    predict_df = pd.DataFrame([data])

    predict_mood_df = predict_df[['danceability', 'energy', 'valence']]
    new_mood_df_scaled = scaler.transform(predict_mood_df)
    mood = kmeans.predict(new_mood_df_scaled)

    return mood

def emotion_classification(data_path=DATA_FILE, data_path2=DATA_FILE2):
    with open(data_path, encoding="utf-8") as f:
        data = json.loads(f.read())

    music_df = pd.DataFrame(data)
    music_df = music_df.replace('', np.NaN)
    music_df = music_df.dropna(axis=0)

    emotion_df = pd.read_csv(data_path2, encoding='cp949')
    df = pd.merge(music_df, emotion_df, on=["title", "singer"])

    X = df[['energy', 'danceability', 'tempo']]
    y = df['emotion']


    clf = DecisionTreeClassifier()
    clf.fit(X, y)

    pred_X = music_df[['energy', 'danceability', 'tempo']]
    predicted_emotion = clf.predict(pred_X)
    predicted_emotion

    return predicted_emotion

def favorite_song(data_path=DATA_FILE, data_path3=DATA_FILE3):
    with open(data_path, encoding="utf-8") as f:
        data = json.loads(f.read())
    
    music_df = pd.DataFrame(data)
    music_df = music_df.replace('', np.NaN)
    music_df = music_df.dropna(axis=0)

    with open(data_path3, encoding="utf-8") as f:
        data2 = json.loads(f.read())

    favorite_df = pd.DataFrame(data2)
    favorite_df = favorite_df.dropna(axis=0)
    favorite_df = favorite_df[['user_id', 'title', 'liked']]

    tab = pd.crosstab(favorite_df['user_id'], favorite_df['title'])
    favorite_df_group = favorite_df.groupby(['user_id', 'title'])
    tab = favorite_df_group.sum().unstack()

    reader = Reader(rating_scale=(0, 1))
    data = Dataset.load_from_df(df=favorite_df, reader=reader)

    train = data.build_full_trainset()
    test = train.build_testset()

    model = SVD(n_factors=100, n_epochs=20, random_state=123)
    model.fit(train)

    user_list = favorite_df['user_id'].unique()
    recommendations = {}

    for user_id in user_list:
        liked_songs = favorite_df.loc[(favorite_df['user_id'] == user_id) & (favorite_df['liked'] == 1), 'id'].tolist()
        all_songs = favorite_df.loc[~favorite_df['id'].isin(liked_songs), 'id'].tolist()
        
        scores = [(song, model.predict(user_id, song).est) for song in all_songs]
        recommendation_list = [score[0] for score in sorted(scores, key=lambda x: x[1], reverse=True)[:20] if score[0] not in liked_songs]

        recommendations[user_id] = recommendation_list
    
    return recommendation_list
