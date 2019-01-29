dependencies {
    // Immutables
    annotationProcessor("org.immutables:value")
    compileOnly("org.immutables:value-annotations")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}