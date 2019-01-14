rootProject.name = "pinboard-map"

// https://docs.gradle.org/current/userguide/multi_project_builds.html
include("services:location-service:location-api")
include("services:location-service:location-schema")
include("services:location-service:location-svc")
include("services:pinboardmap-web")