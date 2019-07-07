FROM java

VOLUME /tmp

ADD build/libs/restservice-0.0.1-SNAPSHOT.jar spring-boot-app.jar

ENTRYPOINT ["java","-jar","/spring-boot-app.jar"]
