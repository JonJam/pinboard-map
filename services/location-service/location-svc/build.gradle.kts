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