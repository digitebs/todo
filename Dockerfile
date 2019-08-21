FROM java:8

WORKDIR /

ADD target/todo-1.0-SNAPSHOT-jar-with-dependencies.jar todo-1.0-SNAPSHOT-jar-with-dependencies.jar

EXPOSE 4567

ENTRYPOINT ["java", "-jar", "todo-1.0-SNAPSHOT-jar-with-dependencies.jar"]