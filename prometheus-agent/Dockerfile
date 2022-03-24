FROM busybox:1.34.1
VOLUME ["/prometheus-javaagent"]
COPY /jmx_prometheus_javaagent-0.16.1.jar /prometheus-javaagent/jmx_prometheus_javaagent-0.16.1.jar
COPY /config.yaml /prometheus-javaagent/config.yaml
#RUN cp /jmx_prometheus_javaagent-0.16.1.jar /prometheus-javaagent/jmx_prometheus_javaagent-0.16.1.jar

# | cp /config.yaml /prometheus-javaagent/config.yaml
#CMD ["cp", "/jmx_prometheus_javaagent-0.16.1.jar", "/prometheus-javaagent/jmx_prometheus_javaagent-0.16.1.jar"]
#CMD ["cp", "/config.yaml", "/prometheus-javaagent/config.yaml"]