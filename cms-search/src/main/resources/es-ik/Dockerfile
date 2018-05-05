FROM docker.elastic.co/elasticsearch/elasticsearch-oss:6.2.3

ENV ES_JAVA_OPTS="-Xms128m -Xmx128m"

COPY jvm.options /usr/share/elasticsearch/config/jvm.options

RUN elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v6.2.3/elasticsearch-analysis-ik-6.2.3.zip
