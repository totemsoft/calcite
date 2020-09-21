# Totem Software Pty Ltd

## Apache Calcite

* Single query that can talk to multiple SQL data sources such as mysql and postgres and REST.
* Wherever possible it should pushdown filters and joins to the underlying data sources instead of letting Calcite do it in memory.
* Existing Calcite Adapter needs to be tweaked and new optimization rules need to be developed.

## Dockerizing a Spring Boot Application
```
#
# assemble a runnable jar file and copy it to our Docker build-directory:
mvn package spring-boot:repackage
# create an image from our Dockerfile:
docker build --file=Dockerfile.server --tag=calcite-sql:latest --rm=true .
# create a volume for mounting:
docker volume create --name=calcite-sql
# run the container from our image:
docker run --rm --name=calcite-sql --publish=80:8080 \
  --volume=calcite-sql:/var/lib/calcite/config \
  calcite-sql:latest
# inspect details, stop and remove
docker inspect calcite-sql
docker stop calcite-sql
docker rm calcite-sql
```

## Links
