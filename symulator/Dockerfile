FROM openjdk:15-slim
ADD build/libs/symulator-0.0.1-SNAPSHOT.jar ./symulator.jar
ADD config.cfg ./
EXPOSE 8080
CMD java -jar ./symulator.jar