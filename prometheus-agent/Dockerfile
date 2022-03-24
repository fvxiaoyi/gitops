FROM busybox:1.34.1
COPY /jmx_prometheus_javaagent-0.16.1.jar /jmx_prometheus_javaagent-0.16.1.jar
COPY /config.yaml /config.yaml
VOLUME ["/prometheus-javaagent"]
CMD ["cp", "/opentelemetry-javaagent.jar", "/prometheus-javaagent/opentelemetry-javaagent.jar"]
CMD ["cp", "/config.yaml", "/prometheus-javaagent/config.yaml"]