FROM openjdk:12-alpine
RUN adduser --shell /bin/sh --disabled-password --gecos "" giphy
WORKDIR /home/giphy

ENV JAVA_OPTS -Xmx8g \
              -Xms8g \
              -XX:+UseStringDeduplication \
              -XX:-UseParallelGC \
              -XX:-UseConcMarkSweepGC \
              -XX:+UseG1GC \
              -XX:MaxGCPauseMillis=200 \
              -XX:ParallelGCThreads=20 \
              -XX:ConcGCThreads=5 \
              -XX:InitiatingHeapOccupancyPercent=70

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/home/giphy/app.jar"]