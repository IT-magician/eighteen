#! /bin/sh
set -e

INDEX_NAME=tj_song_list_idx
ID=elastic
PASSWD=b304b304

curl -u ${ID}:${PASSWD} -XPUT -H 'Content-Type: application/json' http://localhost:9200/${INDEX_NAME}/ -d '{
  "settings": {
    "index": {
      "analysis": {
        "filter": {
          "suggest_filter": {
            "type": "edge_ngram",
            "min_gram": 1,
            "max_gram": 50
          }
        },
        "tokenizer": {
          "jaso_search_tokenizer": {
            "type": "jaso_tokenizer",
            "mistype": true,
            "chosung": false
          },
          "jaso_index_tokenizer": {
            "type": "jaso_tokenizer",
            "mistype": true,
            "chosung": true
          }
        },
        "analyzer": {
          "suggest_search_analyzer": {
            "type": "custom",
            "tokenizer": "jaso_search_tokenizer"
          },
          "suggest_index_analyzer": {
            "type": "custom",
            "tokenizer": "jaso_index_tokenizer",
            "filter": [
              "suggest_filter"
            ]
          }
        }
      }
    }
  }
}'

curl -u ${ID}:${PASSWD} -XPUT -H 'Content-Type: application/json' http://localhost:9200/${INDEX_NAME}/_mapping -d '{
  "properties": {
    "title": {
      "type": "text",
      "store": true,
      "analyzer": "suggest_index_analyzer",
      "search_analyzer": "suggest_search_analyzer"
    },
    
    "singer": {
      "type": "text",
      "store": true,
      "analyzer": "suggest_index_analyzer",
      "search_analyzer": "suggest_search_analyzer"
    }
  }
}'


curl -u ${ID}:${PASSWD} -H 'Content-Type: application/x-ndjson' -XPOST 'localhost:9200/_bulk?pretty' --data-binary @data/song_monthNew-for-elasticsearch_bulk_insert.json