FROM amazoncorretto:17.0.7-alpine
USER root
RUN rm -f /etc/localtime && ln -s /usr/share/zoneinfo/America/Guayaquil /etc/localtime
COPY *.jar /deployments/
COPY application.properties /deployments/data/

RUN chgrp -R 0 /deployments && chmod -R g=u /deployments
EXPOSE 8080
USER 1001

CMD ["java","-jar", "/deployments/client-service-0.0.1-SNAPSHOT.jar", "--spring.config.location=file:///deployments/data/application.properties"]
