# Build stage
FROM alpine:latest AS build
WORKDIR /app
COPY . .
# Installing build dependencies
RUN apk add --no-cache openjdk21
RUN mvn clean package -DskipTests

# Run stage
FROM alpine:latest
# Installing Java Runtime
RUN apk add --no-cache openjdk21-jre

ARG PROFILE
ENV JAR_NAME=demo-park-api \
    SERVER_PORT=8081 \
    SPRING_PROFILES_ACTIVE=$PROFILE

COPY --from=build /app/target/demo-park-api.jar /app/demo-park-api.jar

CMD java -jar /app/demo-park-api.jar