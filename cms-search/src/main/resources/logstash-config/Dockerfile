FROM docker.elastic.co/logstash/logstash-oss:6.2.4

ENV MYSQL_JDBC_VERSION=6.0.6 \
    DB_SERVER=localhost \
    DB_PORT=3306 \
    DB_PASSWORD="$%^YGR^&UHGT^&U" \
    DB_USER="root" \
    ES_SERVER="localhost" \
    ES_PORT=9200

COPY * /usr/share/logstash/data/
ADD http://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-${MYSQL_JDBC_VERSION}.tar.gz /usr/share/logstash/data/mysql
CMD logstash -f /usr/share/logstash/data/article.config