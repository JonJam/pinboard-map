// Set root project name. See https://docs.gradle.org/current/userguide/intro_multi_project_builds.html#sec:structure_of_a_multiproject_build
rootProject.name = "pinboard-map"

// https://docs.gradle.org/current/userguide/multi_project_builds.html
include("common:common-configuration")
include("common:common-database")
include("common:common-exception")
include("common:common-feign")
include("common:common-logging")
include("common:common-mapstruct")
include("common:common-objectmodel")
include("common:common-service")
include("common:common-test")

include("services:business-services:location-service:location-api")
include("services:business-services:location-service:location-schema")
include("services:business-services:location-service:location-svc")

include("services:edge-services:pinboard-map-web")