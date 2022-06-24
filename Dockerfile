FROM adoptopenjdk/openjdk16:alpine-jre
ARG JAR_FILE=target/VotingService-0.0.1-SNAPSHOT.jar
WORKDIR /opt/vote_service
COPY ${JAR_FILE} vote_service.jar
ENTRYPOINT ["java","-jar","vote_service.jar"]