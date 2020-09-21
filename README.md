# Totem Software Pty Ltd

## Apache Calcite

* Single query that can talk to multiple SQL data sources such as mysql and postgres and REST.
* Wherever possible it should pushdown filters and joins to the underlying data sources instead of letting Calcite do it in memory.
* Existing Calcite Adapter needs to be tweaked and new optimization rules need to be developed.

## Dockerizing a Spring Boot Application
```
# Buildpacks Support
mvn spring-boot:build-image
# Common Base Image
docker build --tag=alpine-java:base --rm=true .

# Dockerize Dependent Applications in a Composite
docker-compose config
docker-compose up --build
docker-compose down

# some useful docker commands:
docker ps -a
docker rm <container_id>
docker images
docker image prune
docker image rm <image_id>
```
