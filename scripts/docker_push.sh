#!/bin/bash

docker login -u="$DOCKER_USERNAME" -p="$DOCKER_PASSWORD"
docker build -t pinboard-map-base-tomcat-container --build-arg env=dev ./base-tomcat-container/.
docker build -t pinboard-map-location-service ./services/location-service/.
docker images
docker tag pinboard-map-base-tomcat-container $DOCKER_USERNAME/pinboard-map-base-tomcat-container:latest
docker tag pinboard-map-base-tomcat-container $DOCKER_USERNAME/pinboard-map-base-tomcat-container:0.0.$TRAVIS_BUILD_NUMBER
docker tag pinboard-map-location-service $DOCKER_USERNAME/pinboard-map-location-service:latest
docker tag pinboard-map-location-service $DOCKER_USERNAME/pinboard-map-location-service:0.0.$TRAVIS_BUILD_NUMBER
docker push $DOCKER_USERNAME/pinboard-map-base-tomcat-container
docker push $DOCKER_USERNAME/pinboard-map-location-service