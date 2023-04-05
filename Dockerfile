FROM python:3.8

WORKDIR /code

COPY flask/ .

RUN /usr/local/bin/python -m pip install --upgrade pip
RUN pip install -r ./requirements.txt

CMD ["python", "./app.py"]