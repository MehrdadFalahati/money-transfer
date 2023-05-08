# Money Transfer Application
Transfer Money Application is an api for transferring money between 2 accounts

## Application Diagram
implementing application with **Hexagonal** architecture.

![Application Diagram](Application-Diagram.png)

## Installation
* start postgres database from `infrastructure/docker-compose`
* run `docker-compose -f postgres-sql.yml up`
* run this script
```bash
DROP SCHEMA IF EXISTS money_transfer CASCADE;

CREATE SCHEMA money_transfer;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
```
* run `mvn clean install` for creating docker image.

## Swagger
Dashboard swagger in project is : http://localhost:8686/swagger-ui/index.html