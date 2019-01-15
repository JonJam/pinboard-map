plugins {
    java
}

repositories {
    jcenter()
}

// Use dependency constraints. See https://docs.gradle.org/current/userguide/declaring_dependencies.html#declaring_a_dependency_without_version
dependencies {
    constraints {
        implementation("org.glassfish.jersey.containers:jersey-container-grizzly2-http:2.27")
        implementation("org.glassfish.jersey.inject:jersey-hk2:2.27")
        implementation("org.glassfish.jersey.media:jersey-media-json-jackson:2.27")
        implementation("org.glassfish.hk2:guice-bridge:2.5.0-b62")
        testImplementation("junit:junit:4.9")
    }
}

dependencies {
    implementation("org.glassfish.jersey.containers:jersey-container-grizzly2-http")
    implementation("org.glassfish.jersey.inject:jersey-hk2")
    implementation("org.glassfish.jersey.media:jersey-media-json-jackson")
    implementation("org.glassfish.hk2:guice-bridge")
    testImplementation("junit:junit")
}

// Performance suggestions for Java Projects. See https://guides.gradle.org/performance/#suggestions_for_java_projects
tasks.withType<JavaCompile> {
    options.isFork = true
}

tasks.withType<Test> {
    maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
}

tasks.withType<Test> {
    setForkEvery(100)
}

tasks.withType<Test> {
    reports.html.isEnabled = false
    reports.junitXml.isEnabled = false
}