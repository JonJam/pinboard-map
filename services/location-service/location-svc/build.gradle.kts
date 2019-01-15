plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.glassfish.jersey.containers:jersey-container-grizzly2-http:2.27")
    implementation("org.glassfish.jersey.inject:jersey-hk2:2.27")
    implementation("org.glassfish.jersey.media:jersey-media-json-jackson:2.27")
    implementation("org.glassfish.hk2:guice-bridge:2.5.0-b62")
    testImplementation("junit:junit:4.9")
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