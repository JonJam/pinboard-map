// Set root project name. See https://docs.gradle.org/current/userguide/intro_multi_project_builds.html#sec:structure_of_a_multiproject_build
rootProject.name = "pinboard-map"

// https://docs.gradle.org/current/userguide/multi_project_builds.html
include("services:location-service:location-api")
include("services:location-service:location-schema")
include("services:location-service:location-svc")
include("services:pinboard-map-web")