#!/bin/bash

docker login -u="$DOCKER_USERNAME" -p="$DOCKER_PASSWORD"

# Base dev image
docker build -t pinboard-map-base-tomcat-container:dev --build-arg env=dev ./base-tomcat-container/.
docker tag pinboard-map-base-tomcat-container:dev $DOCKER_USERNAME/pinboard-map-base-tomcat-container:dev
docker tag pinboard-map-base-tomcat-container:dev $DOCKER_USERNAME/pinboard-map-base-tomcat-container:dev_0.0.$TRAVIS_BUILD_NUMBER
docker push $DOCKER_USERNAME/pinboard-map-base-tomcat-container:dev

# Base prod image
docker build -t pinboard-map-base-tomcat-container:prod --build-arg env=prod ./base-tomcat-container/.
docker tag pinboard-map-base-tomcat-container:prod $DOCKER_USERNAME/pinboard-map-base-tomcat-container:latest
docker tag pinboard-map-base-tomcat-container:prod $DOCKER_USERNAME/pinboard-map-base-tomcat-container:prod
docker tag pinboard-map-base-tomcat-container:prod $DOCKER_USERNAME/pinboard-map-base-tomcat-container:prod_0.0.$TRAVIS_BUILD_NUMBER
docker push $DOCKER_USERNAME/pinboard-map-base-tomcat-container:prod

# Location image
docker build -t pinboard-map-location-service --build-arg env=prod_0.0.$TRAVIS_BUILD_NUMBER ./services/location-service/.
docker tag pinboard-map-location-service $DOCKER_USERNAME/pinboard-map-location-service:latest
docker tag pinboard-map-location-service $DOCKER_USERNAME/pinboard-map-location-service:0.0.$TRAVIS_BUILD_NUMBER
docker push $DOCKER_USERNAME/pinboard-map-location-service