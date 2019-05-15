plugins {
    war
}

dependencies {
    // Jersey
    implementation("org.glassfish.jersey.containers:jersey-container-servlet")
    implementation("org.glassfish.jersey.inject:jersey-hk2")
    implementation("javax.xml.bind:jaxb-api")
    implementation("javax.activation:activation")

    // Jackson
    implementation("org.glassfish.jersey.media:jersey-media-json-jackson")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8")
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    // Guice
    implementation("com.google.inject.extensions:guice-servlet")
    implementation("org.glassfish.hk2:guice-bridge")

    // Immutables
    annotationProcessor("org.immutables:value")
    compileOnly("org.immutables:value-annotations")

    // Project dependencies
    // Used as annotation processor for Immutables custom annotation.
    annotationProcessor(project(":common:common-objectmodel"))
    implementation(project(":common:common-objectmodel"))
    implementation(project(":services:location-service:location-api"))

    // JUnit
    testImplementation("org.junit.jupiter:junit-jupiter-api")

    // Hamcrest
    testImplementation("org.hamcrest:hamcrest-library")

    // Mockito
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-junit-jupiter")

    // TestContainers
    testImplementation("org.testcontainers:testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")

    // Feign
    testImplementation("io.github.openfeign:feign-core")
    testImplementation("io.github.openfeign:feign-jackson")
    testImplementation("io.github.openfeign:feign-jaxrs")

    // JUnit
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks {
    register<Copy>("explodedWar") {
        group = "Custom"
        description = "Copies contents of a WAR into a directory"

        dependsOn("war")

        from(zipTree("$buildDir/libs/${project.name}-${project.version}.war"))
        into("$buildDir/exploded")
    }

    named("assemble") {
        dependsOn("explodedWar")
    }
}