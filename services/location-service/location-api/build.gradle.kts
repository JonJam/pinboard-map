dependencies {
    // Immutables
    annotationProcessor("org.immutables:value")
    compileOnly("org.immutables:value-annotations")

    // Project dependencies
    // Used as annotation processor for Immutables custom annotation.
    annotationProcessor(project(":common:common-objectmodel"))
    implementation(project(":common:common-objectmodel"))
}