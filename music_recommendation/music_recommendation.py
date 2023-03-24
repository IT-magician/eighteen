import os
import shutil

import pandas as pd
from sklearn.preprocessing import MinMaxScaler
from sklearn.cluster import KMeans
import json
from pandas import json_normalize
import numpy as np
from sklearn.tree import DecisionTreeClassifier

DATA_DIR = "../data"
DATA_FILE = os.path.join(DATA_DIR, "results.json")

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
