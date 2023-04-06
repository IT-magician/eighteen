ARG ELASTIC_VERSION

# https://www.docker.elastic.co/
FROM docker.elastic.co/logstash/logstash:${ELASTIC_VERSION}

COPY ./connector-j/mysql-connector-j-8.0.32.jar /usr/share/logstash/logstash-core/lib/jars/

# Add your logstash plugins setup here
# Example: RUN logstash-plugin install logstash-filter-json
