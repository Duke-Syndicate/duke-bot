FROM eclipse-temurin:17

RUN mkdir -p /opt/duke-syndicate/duke-bot
COPY target/untitled-1.0-SNAPSHOT.jar /opt/duke-syndicate/duke-bot/app.jar
COPY target/final /opt/duke-syndicate/duke-bot

WORKDIR /opt/duke-syndicate/duke-bot

CMD ["java", "-jar", "/opt/duke-syndicate/duke-bot/app.jar"]