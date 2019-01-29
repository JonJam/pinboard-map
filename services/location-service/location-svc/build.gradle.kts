dependencies {
    // Jersey
    implementation("org.glassfish.jersey.containers:jersey-container-servlet")
    implementation("org.glassfish.jersey.inject:jersey-hk2")
    implementation("javax.xml.bind:jaxb-api")
    implementation("javax.activation:activation")

    // Jackson
    implementation("org.glassfish.jersey.media:jersey-media-json-jackson")

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

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}