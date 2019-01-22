dependencies {
    "implementation"("org.glassfish.jersey.containers:jersey-container-servlet")
    //implementation("org.glassfish.jersey.containers:jersey-container-grizzly2-http")
    //implementation("org.glassfish.jersey.inject:jersey-hk2")
    //implementation("org.glassfish.jersey.media:jersey-media-json-jackson")
    //implementation("org.glassfish.hk2:guice-bridge")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}