dependencies {
    // Immutables
    annotationProcessor("org.immutables:value")
    compileOnly("org.immutables:value-annotations")

    // Jackson
    implementation("org.glassfish.jersey.media:jersey-media-json-jackson")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}