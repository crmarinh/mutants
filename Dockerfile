FROM openjdk:11
USER root
COPY /build/libs/mutants-1.0.0.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar app.jar" ]
