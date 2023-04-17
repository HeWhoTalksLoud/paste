FROM amazoncorretto:11
RUN mkdir -p /db
ENV SPRING_DATASOURCE_URL=jdbc:h2:/db/paste
WORKDIR /
COPY target/paste-0.0.1-SNAPSHOT.jar /paste.jar
CMD java -jar paste.jar
