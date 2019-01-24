dependencies {
    // Jersey
    implementation("org.glassfish.jersey.containers:jersey-container-servlet")
    implementation("javax.xml.bind:jaxb-api:2.3+")
    implementation("javax.activation:activation:1.1+")

    // Jackson
    implementation("org.glassfish.jersey.media:jersey-media-json-jackson")

    implementation("org.glassfish.jersey.inject:jersey-hk2")
    //implementation("org.glassfish.hk2:guice-bridge")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}