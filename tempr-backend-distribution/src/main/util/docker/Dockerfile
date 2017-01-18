FROM ubuntu:16.04
MAINTAINER Szabo Zsolt

#Install Java
RUN apt-get update && \
   apt-get upgrade -y && \
   apt-get install -y  software-properties-common && \
   add-apt-repository ppa:webupd8team/java -y && \
   apt-get update && \
   echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
   apt-get install -y oracle-java8-installer && \
   apt-get clean

# Add server binary
ADD tempr-backend-api-1.0.0-SNAPSHOT.jar api.jar

# Not changed flag by default in docker.
RUN sh -c 'touch /app.jar'

ENTRYPOINT ["java", "-jar", "/app.jar"]