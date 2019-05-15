dependencies {
    // Immutables
    annotationProcessor("org.immutables:value")
    compileOnly("org.immutables:value-annotations")

    // Jackson
    implementation("com.fasterxml.jackson.core:jackson-core")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.core:jackson-annotations")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.hamcrest:hamcrest-library")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}