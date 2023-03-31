from flask import Flask
from flask_cors import CORS

from dotenv import load_dotenv
import os

from sqlalchemy import create_engine
from sqlalchemy.orm import scoped_session, sessionmaker
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column, Integer, String
 
app = Flask(__name__)
CORS(app, supports_credentials=True)

load_dotenv()
mysql_url = "mysql+pymysql://" + os.environ.get('DB_USER') + ":"+ os.environ.get('DB_PASS') + "@" + os.environ.get('DB_URL') +"?charset=utf8"
engine = create_engine(mysql_url, echo=True, convert_unicode=True)
@app.route('/flask', methods=['GET'])
def index():
    print(mysql_url)
    return {"members": [{ "id" : 1, "name" : "yerin" },
    					{ "id" : 2, "name" : "dalkong" }]}
 
 
app.run(debug=True)