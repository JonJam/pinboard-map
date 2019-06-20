/*
 * This file was generated by the Gradle 'init' task.
 *
 * This is a general purpose Gradle build.
 * Learn how to create Gradle builds at https://guides.gradle.org/creating-new-gradle-builds/
 */

plugins {
    id("org.sonarqube") version "2.7"
    id("net.ltgt.apt-idea") version "0.20" apply false
}

// Enabling dependency locking. See https://docs.gradle.org/current/userguide/dependency_locking.html.
allprojects {
    repositories {
        jcenter()
    }

    // Locking for configurations. See https://docs.gradle.org/current/userguide/dependency_locking.html.
    dependencyLocking {
        lockAllConfigurations()
    }

    buildscript {
        dependencyLocking {
            lockAllConfigurations()
        }
    }

    // Declare version. See https://docs.gradle.org/current/userguide/building_java_projects.html#introduction
    version = "0.0.1"

    configurations.all {
        resolutionStrategy {
            // Fail eagerly on version conflict (includes transitive dependencies)
            failOnVersionConflict()
        }
    }

    sonarqube {
        properties {
            property("sonar.organization", "jonjam-github")
            property("sonar.projectName", "pinboard-map")
            property("sonar.projectKey", "pinboard-map")
            property("sonar.host.url", "https://sonarcloud.io")
            property("sonar.junit.reportPaths", "build/tests-results/test")
            property("sonar.coverage.jacoco.xmlReportPaths", "build/jacoco")
            
            versionDetails()
        }
    }
}

subprojects {
    // TODO Refactor below as and when to only be common stuff.
    apply(plugin = "java")
    apply(plugin = "checkstyle")
    apply(plugin = "net.ltgt.apt-idea")
    apply(plugin = "jacoco")

    // Use dependency constraints. See https://docs.gradle.org/current/userguide/declaring_dependencies.html#declaring_a_dependency_without_version
    dependencies {
        constraints {
            // Jersey
            "implementation"("org.glassfish.jersey.containers:jersey-container-servlet:2.28+")
            "implementation"("org.glassfish.jersey.inject:jersey-hk2:2.28+")
            // javax.xml.bind:jaxb-api and javax.activation:activation are needed to prevent errors. See https://www.jeffryhouser.com/index.cfm/2017/12/21/Why-wont-Jersey-work-on-JDK-9
            "implementation"("javax.xml.bind:jaxb-api:2.3+")
            "implementation"("javax.activation:activation:1.1+")

            // Jackson
            "implementation"("org.glassfish.jersey.media:jersey-media-json-jackson:2.28")
            "implementation"("com.fasterxml.jackson.core:jackson-core:2.9.8")
            "implementation"("com.fasterxml.jackson.core:jackson-databind:2.9.8")
            "implementation"("com.fasterxml.jackson.core:jackson-annotations:2.9.8") {
                // Resolving conflict
                isForce = true
            }
            // Enabling Java 8 - https://github.com/FasterXML/jackson-modules-java8
            "implementation"("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.9.8")
            "implementation"("com.fasterxml.jackson.module:jackson-module-parameter-names:2.9.8")
            "implementation"("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.9.8")

            // Guice
            "implementation"("com.google.inject.extensions:guice-servlet:4.2.2")
            "implementation"("org.glassfish.hk2:guice-bridge:2.5.0")
            "implementation"("com.google.inject:guice:4.2.2") {
                // Resolving conflict - guice-servlet / guice-bridge
                isForce = true
            }

            // Immutables. See for using apt plugin to set up: https://github.com/tbroyer/gradle-apt-plugin#usage-with-ides
            "annotationProcessor"("org.immutables:value:2.7+")
            "compileOnly"("org.immutables:value-annotations:2.7+")

            // JUnit. See for setup: https://docs.gradle.org/current/userguide/java_testing.html#using_junit5
            "testImplementation"("org.junit.jupiter:junit-jupiter-api:5.4.2") {
                // Resolving conflict - this / testcontainers
                isForce = true
            }
            "testRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine:5.4.2")

            // Hamcrest
            "testImplementation"("org.hamcrest:hamcrest-library:2.1")
            "testImplementation"("org.hamcrest:hamcrest-core:2.1") {
                // Only specified to resolve conflict - hamcrest-library / testcontainers
                isForce = true
            }

            // Mockito
            "testImplementation"("org.mockito:mockito-core:2.26+")
            "testImplementation"("org.mockito:mockito-junit-jupiter:2.26+")

            // TestContainers
            "testImplementation"("org.testcontainers:testcontainers:1.11.2")
            "testImplementation"("org.testcontainers:junit-jupiter:1.11.2")
            "testImplementation"("org.slf4j:slf4j-api:1.7.26") {
                // Only specified to resolve conflict between testcontainers libs.
                isForce = true
            }

            // Feign
            "testImplementation"("io.github.openfeign:feign-core:10.2+")
            "testImplementation"("io.github.openfeign:feign-jackson:10.2+")
            "testImplementation"("io.github.openfeign:feign-jaxrs:10.2+")

        }
    }

    // Set compatibility. See https://docs.gradle.org/current/userguide/building_java_projects.html#introduction
    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    // Performance suggestions for Java projects. See https://guides.gradle.org/performance/#suggestions_for_java_projects
    tasks.withType<JavaCompile> {
        options.isFork = true
    }

    tasks.withType<Test> {
        // JUnit - setup. See https://docs.gradle.org/current/userguide/java_testing.html#using_junit5
        useJUnitPlatform()

        // JUnit - parralel execution. See https://junit.org/junit5/docs/current/user-guide/#writing-tests-parallel-execution
        systemProperty("junit.jupiter.execution.parallel.enabled", "true")
        systemProperty("junit.jupiter.execution.parallel.mode.default", "concurrent")

        // Performance suggestions for Java projects. See https://guides.gradle.org/performance/#suggestions_for_java_projects
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
        setForkEvery(100)
        reports.html.isEnabled = false
        reports.junitXml.isEnabled = true
    }
}