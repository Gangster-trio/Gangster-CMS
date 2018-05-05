FROM openjdk:alpine
#eureka 服务器地址
ENV EUREKA_SERVER=localhost \
#eureka 服务器端口
    EUREKA_PORT=8761 \
#数据库服务器地址
    DB_SERVER=localhost \
#数据库服务端口
    DB_PORT=3306 \
#数据库服务器密码
    DB_PASSWORD="$%^YGR^&UHGT^&U" \
#图片等文件的存储路径
    FILE_PAHT=/data \
#服务器端口
    PORT=8080 \
#admin的版本
    VERSION=0.1

VOLUME /data/cms

COPY target/cms-admin-${VERSION}.jar /app/cms-admin.jar

ENTRYPOINT [ "java","-jar","/app/cms-admin.jar" \
            ,"--db.server=${DB_SERVER}" \
            ,"--db.port=${DB_PORT}" \
            ,"--db.password=${DB_PASSWORD}" \
            ,"--eureka_server=${EUREKA_SERVER}" \
            ,"--eureka_port=${EUREKA_PORT}" \
            ,"--server.port=${PORT}" ]
EXPOSE ${PORT}