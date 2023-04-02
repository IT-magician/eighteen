from flask import Flask, jsonify
from flask_cors import CORS

from dotenv import load_dotenv
import os

from sqlalchemy import create_engine
from sqlalchemy.orm import scoped_session, sessionmaker
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column, Integer, String
 
import pandas as pd
import numpy as np
from sklearn.tree import DecisionTreeClassifier
import json
from surprise import Reader, Dataset
from surprise import SVD


app = Flask(__name__)
CORS(app, supports_credentials=True)

DATA_DIR = "data"
DATA_FILE = os.path.join(DATA_DIR, "results.json")
DATA_FILE2 = os.path.join(DATA_DIR, "test.csv")
DATA_FILE3 = os.path.join(DATA_DIR, "user_playlist.json")

load_dotenv()
mysql_url = "mysql+pymysql://" + os.environ.get('DB_USER') + ":"+ os.environ.get('DB_PASS') + "@" + os.environ.get('DB_URL') +"?charset=utf8"
engine = create_engine(mysql_url, echo=True)

Base = declarative_base()


class Music(Base):
    __tablename__ = 'music'

    music_id = Column(Integer, primary_key=True)
    title = Column(String)
    singer = Column(String)
    thumbnail_url = Column(String)


class Music_Feature(Base):
    __tablename__ = 'music_feature'

    music_id = Column(Integer, primary_key=True)
    dance_ability = Column(Integer)
    energy = Column(Integer)
    valence = Column(Integer)
    key = Column(Integer)
    popularity = Column(Integer)
    tempo = Column(Integer)

class E_Music(Base):
    __tablename__ = 'e_music'

    music_id = Column(Integer, primary_key=True)
    popularity = Column(Integer)
    emotion = Column(Integer)

class MyEighteen(Base):
    __tablename__ = 'my_eighteen'

    my_eighteen_id = Column(Integer, primary_key=True)
    music_id = Column(Integer)
    user_id = Column(Integer)

Base.metadata.bind = engine
DBSession = sessionmaker(bind=engine)

@app.route('/flask/insert')
def dataInsert(data_path=DATA_FILE):
    with open(data_path, encoding="utf-8") as f:
        data = json.loads(f.read())
    
    with DBSession() as session:
        for music_data in data:
            music_id = music_data['id']

            # Check if the music_id already exists in the database
            existing_music = session.query(Music).filter(Music.music_id == music_id).first()

            if not existing_music:
                music = Music(music_id=music_id, title=music_data['title'], singer=music_data['singer'], thumbnail_url=music_data['thumbnail_url'])
                session.add(music)

                # Handle empty values
                danceability = music_data['danceability'] if music_data['danceability'] != '' else 0
                energy = music_data['energy'] if music_data['energy'] != '' else 0
                valence = music_data['valence'] if music_data['valence'] != '' else 0
                key = music_data['key'] if music_data['key'] != '' else 0
                tempo = music_data['tempo'] if music_data['tempo'] != '' else 0

                music_feature = Music_Feature(music_id=music.music_id, dance_ability=danceability, energy=energy, valence=valence, key=key, popularity=music_data['popularity'], tempo=tempo)
                session.add(music_feature)

        try:
            session.commit()
        except Exception as e:
            session.rollback()
            print(f"An error occurred while inserting data: {e}")

    return "Success"

@app.route('/flask/emotion')
def emotion_classification(data_path2=DATA_FILE2):
    with DBSession() as session:
        music_features = session.query(Music_Feature).all()

    music_df = pd.DataFrame([mf.__dict__ for mf in music_features])

    emotion_df = pd.read_csv(data_path2, encoding='cp949')
    df = pd.merge(music_df, emotion_df, on=["music_id"])

    X = df[['energy', 'dance_ability', 'valence']]
    y = df['emotion']

    clf = DecisionTreeClassifier()
    clf.fit(X, y)

    pred_X = music_df[['energy', 'dance_ability', 'valence']]
    predicted_emotion = clf.predict(pred_X)

    for music_id, popularity, emotion in zip(music_df['music_id'], music_df['popularity'], predicted_emotion):
        e_music = E_Music(music_id=music_id, popularity=popularity, emotion=emotion)
        session.add(e_music)

    try:
        session.commit()
    except Exception as e:
        session.rollback()
        print(f"An error occurred while inserting data: {e}")

    return "ss"

@app.route('/flask/favorite')
def favorite_song(data_path=DATA_FILE, data_path3=DATA_FILE3):
    # with DBSession() as session:
    #     music_features = session.query(Music_Feature).all()
    
    # music_df = pd.DataFrame([mf.__dict__ for mf in music_features])

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




@app.route('/flask/music', methods=['GET'])
def test():
    # Do something with the classifier object `clf`
    return jsonify({'result': 'Success'})


if __name__ == "__main__" :
    app.run(host='0.0.0.0', debug=True)