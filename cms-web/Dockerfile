FROM openjdk:alpine
#eureka 服务器地址
ENV DB_SERVER=localhost \
    DB_PORT=3306 \
#数据库服务器密码
    DB_USER=root \
    DB_PASSWORD="$%^YGR^&UHGT^&U" \
#图片等文件的存储路径
    FILE_PAHT=/data \
#服务器端口
    PORT=8090

VOLUME /data/cms

COPY target/cms-web.jar /app/cms-web.jar

ENTRYPOINT [ "java","-jar","/app/cms-web.jar" \
            ,"--db.server=${DB_SERVER}" \
            ,"--db.user=${DB_USER}" \
            ,"--db.password=${DB_PASSWORD}" \
            ,"--server.port=${PORT}" ]
EXPOSE ${PORT}