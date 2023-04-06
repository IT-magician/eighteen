ARG ELASTIC_VERSION

# https://www.docker.elastic.co/
FROM docker.elastic.co/elasticsearch/elasticsearch:${ELASTIC_VERSION}


# Add your elasticsearch plugins setup here
# Example: RUN elasticsearch-plugin install analysis-icu

# https://velog.io/@yaincoding/%EC%97%98%EB%9D%BC%EC%8A%A4%ED%8B%B1%EC%84%9C%EC%B9%98-%EC%BB%A4%EC%8A%A4%ED%85%80-%ED%94%8C%EB%9F%AC%EA%B7%B8%EC%9D%B8%EC%9D%84-%EC%9E%91%EC%84%B1%ED%95%98%EA%B3%A0-%EB%B9%8C%EB%93%9C%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95with.-gradle
COPY plugins/jaso-analyzer-plugin-8.6.2-plugin.zip /plugins/jaso-analyzer-plugin-8.6.2-plugin.zip
RUN elasticsearch-plugin install file:///plugins/jaso-analyzer-plugin-8.6.2-plugin.zip
