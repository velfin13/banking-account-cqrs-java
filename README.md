
# Java Microservices with CQRS Event Sourcing MySql | MongoDb | Docker Compose | Spring Boot | Kafka

Banking operations project using CQRS architecture and event source with Kafka

## Installation

1) First we set up the mongo and mysql databases and the kafka broker using docker compose.
> ⚠️ **Warning:** 
> 
> Before starting docker compose make sure to change the ip of the file to the IP of your machine to start the kafka environment
> 

Then, We place ourselves at the root of the project and execute

```
cd docker
```

```
docker compose up -d
```

2) Once the microservices are up, we go to the root folder

```
cd cqrs-es/cqrs.core/
```

```
./mvnw clean && ./mvnw install -DskipTests && ./mvnw package -DskipTests
```

3) We do the same with

```
cd banking-account/
```

```
./mvnw clean && ./mvnw install -DskipTests && ./mvnw package -DskipTests
```