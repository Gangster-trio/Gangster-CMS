FROM openjdk:alpine

ENV EUREKA_SERVER="localhost" \
    EUREKA_PORT="8761" \
    ES_SERVER="localhost" \
    ES_PORT="9300" \
    ES_NAME="elasticsearch" \
    APP_NAME="es" \
    VERSION="0.0.1-SNAPSHOT" \
    SERVER_PORT=2200

COPY target/cms-search-${VERSION}.jar /app/cms-search.jar

EXPOSE ${SERVER_PORT}

ENTRYPOINT ["java","-jar","/app/cms-search.jar" \
            ,"--spring.application.name=${APP_NAME}" \
            ,"--eureka_server=${EUREKA_SERVER}" \
            ,"--eureka_port=${EUREKA_PORT}" \
            ,"--es_server=${ES_SERVER}" \
            ,"--es_port=${ES_PORT}" \
            ,"--es_name=${ES_NAME}" \
            ,"--server.port=${SERVER_PORT}" \
            ]