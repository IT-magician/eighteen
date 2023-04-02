import os

from sqlalchemy import create_engine
from sqlalchemy.orm import scoped_session, sessionmaker
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column, Integer, String
 
import pandas as pd
import numpy as np
from sklearn.tree import DecisionTreeClassifier
import json

DATA_DIR = "data"
DATA_FILE = os.path.join(DATA_DIR, "results.json")
DATA_FILE2 = os.path.join(DATA_DIR, "emotion_results.csv")

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



trained_model = emotion_classification()
for i in range(len(trained_model)):
    print(trained_model[i])