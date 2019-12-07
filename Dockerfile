FROM alpine/git:1.0.7 as clone
WORKDIR /app
RUN git clone https://github.com/FultonBrowne/Ara-Server.git


FROM openjdk:12-jdk-alpine as build
WORKDIR /app
COPY --from=clone /app/Ara-Server /app
RUN ./gradlew build

FROM openjdk:12-jre-alpine
WORKDIR /app
COPY --from=build /app/bin/jar /app
EXPOSE 80
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar Ara-Server.jar"]
