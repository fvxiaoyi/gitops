FROM openjdk:18-jdk-alpine
RUN addgroup --system app && adduser --system --no-create-home --ingroup app app
USER app
VOLUME /app
ARG EXTRACTED=target/extracted
COPY run.sh ./
COPY ${EXTRACTED}/dependencies/ ./
COPY ${EXTRACTED}/spring-boot-loader/ ./
COPY ${EXTRACTED}/snapshot-dependencies/ ./
COPY ${EXTRACTED}/application/ ./
ENTRYPOINT ["sh","./run.sh"]
#ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

#FROM openjdk:18-jdk-alpine
#VOLUME /tmp
#ARG DEPENDENCY=target
#COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY ${DEPENDENCY}/META-INF /app/META-INF
#COPY ${DEPENDENCY}/BOOT-INF/classes /app
#ENTRYPOINT ["java", "-cp","app:app/lib/*","com.example.demo.DemoApplication", "${JAVA_OPTS}"]
