#!/bin/sh

clear;
./bin/fakeversion.sh
./gradlew installDist bootRepackage

docker-compose -f ./docker/compose/docker-compose-dm.yml \
-f ./docker/compose/docker-compose-dm-ports.yml \
-f ./docker/compose/docker-compose-em.yml \
-f ./docker/compose/docker-compose-em-ports.yml \
-f ./docker/compose/docker-compose-idam.yml \
-f ./docker/compose/docker-compose-idam-ports.yml \
down

docker-compose  -f ./docker/compose/docker-compose-dm.yml \
-f ./docker/compose/docker-compose-dm-ports.yml \
-f ./docker/compose/docker-compose-em.yml \
-f ./docker/compose/docker-compose-em-ports.yml \
-f ./docker/compose/docker-compose-idam.yml \
-f ./docker/compose/docker-compose-idam-ports.yml \
pull

docker-compose -f ./docker/compose/docker-compose-dm.yml \
-f ./docker/compose/docker-compose-dm-ports.yml \
-f ./docker/compose/docker-compose-em.yml \
-f ./docker/compose/docker-compose-em-ports.yml \
-f ./docker/compose/docker-compose-idam.yml \
-f ./docker/compose/docker-compose-idam-ports.yml \
up --build
