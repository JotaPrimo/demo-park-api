FROM alpine:latest
#sistema operacional linux basico

RUN apk add  --no-cache openjdk21-jre
# executa um comando dentro container durante a construção da imagem, vou usar para instalar o jre no container

ARG PROFILE
ENV JAR_NAME=demo-park-api \
    SERVER_PORT=8081 \
    SPRING_PROFILES_ACTIVE=$PROFILE

COPY target/demo-park-api.jar /app/demo-park-api.jar
# precisar copiar o artefato .jar para dentro do container, para que seja executadi

CMD java -jar /app/demo-park-api.jar
# roda comandos dentro container, após a construção , vou usar para instalar o jre no container
