FROM gradle:7.5-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
RUN chown -R gradle:gradle /home/gradle
USER gradle
WORKDIR /home/gradle/src
RUN gradle build

FROM openjdk:17-jdk-slim

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/english-cards-0.0.1-SNAPSHOT.jar /app

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XshowSettings:vm", "-Djava.awt.headless=true", "-Djenkins.install.runSetupWizard=false", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/english-cards-0.0.1-SNAPSHOT.jar"]