FROM busybox:1.34.1
COPY /jmx_prometheus_javaagent-0.16.1.jar /jmx_prometheus_javaagent.jar
COPY /config.yaml /config.yaml
VOLUME ["/prometheus-javaagent"]
#RUN cp /jmx_prometheus_javaagent-0.16.1.jar /prometheus-javaagent/jmx_prometheus_javaagent-0.16.1.jar

# | cp /config.yaml /prometheus-javaagent/config.yaml
ENTRYPOINT ["cp", "/jmx_prometheus_javaagent.jar", "/prometheus-javaagent/jmx_prometheus_javaagent.jar", "|" ,"cp", "/config.yaml", "/prometheus-javaagent/config.yaml"]
#ENTRYPOINT ["cp", "/jmx_prometheus_javaagent.jar", "/prometheus-javaagent/jmx_prometheus_javaagent.jar"]