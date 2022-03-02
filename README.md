#SlaVIM chat application

## Technologies used in this project

- Spring Boot
- Spring Data (JPA / Cassandra / Redis)
- Spring Security
- Spring WebSocket
- Spring Session
- Cassandra
- Redis
- RabbitMQ
- PostgreSQL
- JUnit, Mockito and TestContainers (spin up Docker containers for Integration Tests)
- Thymeleaf, JQuery and Bootstrap
- Apache Maven (Surefire and Failsafe plugins)
- Docker


## Running
Local build without IDE:

    $ ./mvnw clean install
    
Local run without IDE:    

    $ ./mvnw spring-boot:run

Local build and run without IDE:    

    $ ./mvnw clean install && ./mvnw spring-boot:run
    

## How to create keyspace and table in cassandra db

dimitriev$ docker exec -it slavim-cassandra /bin/bash
root@random:/# cqlsh -u cassandra -p cassandra
cassandra@cqlsh>
cassandra@cqlsh> show version
cassandra@cqlsh> describe keyspaces
cassandra@cqlsh> CREATE KEYSPACE IF NOT EXISTS slavim_chat WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
cassandra@cqlsh> CREATE TABLE IF NOT EXISTS  slavim_chat.messages (
                      username   text,
                      chatRoomId text,
                      date       timestamp,
                      fromUser   text,
                      toUser     text,
                      text       text,
                      primary key ((username, chatRoomId), date)
                 );