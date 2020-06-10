/*
 * This file was generated by the Gradle 'init' task.
 *
 * This is a general purpose Gradle build.
 * Learn how to create Gradle builds at https://guides.gradle.org/creating-new-gradle-builds/
 */

plugins {
    id("org.sonarqube") version "2.7"
    id("net.ltgt.apt-idea") version "0.20" apply false
    id("org.liquibase.gradle") version "2.0.4" apply false
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
        }
    }
}

subprojects {
    // TODO Refactor below as and when to only be common stuff.
    apply(plugin = "java")
    apply(plugin = "checkstyle")
    apply(plugin = "net.ltgt.apt-idea")
    apply(plugin = "jacoco")

    // Workaround: To get gradle to recognise liquibaseRuntime configuration here where define dependency versions.
    // As plugin (which creates this configuration) is only applied to schema projects.
    configurations {
        create("liquibaseRuntime")
    }

    // Use dependency constraints. See https://docs.gradle.org/current/userguide/declaring_dependencies.html#declaring_a_dependency_without_version
    dependencies {
        constraints {

            val jaxbApiVersion = project.property("javax.xml.bind.jaxb-api.version")
            val jacksonVersion = project.property("com.fasterxml.jackson.version")
            val jerseyVersion = project.property("org.glassfish.jersey.version")
            val log4jVersion = project.property("org.apache.logging.log4j.version")
            val immutablesVersion = project.property("org.immutables.version")
            val junitVersion = project.property("org.junit.jupiter.version")
            val hamcrestVersion = project.property("org.hamcrest.version")
            val mockitoVersion = project.property("org.mockito.version")
            val testcontainersVersion = project.property("org.testcontainers.version")
            val sl4jVersion = project.property("org.slf4j.version")
            val feignVersion = project.property("io.github.openfeign.version")

            // Jersey
            "implementation"("org.glassfish.jersey.containers:jersey-container-servlet:${jerseyVersion}")
            "implementation"("org.glassfish.jersey.inject:jersey-hk2:${jerseyVersion}")
            // javax.xml.bind:jaxb-api and javax.activation:activation are needed to prevent errors. See https://www.jeffryhouser.com/index.cfm/2017/12/21/Why-wont-Jersey-work-on-JDK-9
            "implementation"("javax.xml.bind:jaxb-api:${jaxbApiVersion}")
            "implementation"("javax.activation:activation:1.1+")

            // Jackson
            "implementation"("org.glassfish.jersey.media:jersey-media-json-jackson:${jerseyVersion}")
            "implementation"("com.fasterxml.jackson.core:jackson-core:${jacksonVersion}")
            "implementation"("com.fasterxml.jackson.core:jackson-databind:${jacksonVersion}")
            "implementation"("com.fasterxml.jackson.core:jackson-annotations:${jacksonVersion}") {
                // Resolving conflict
                isForce = true
            }
            // Enabling Java 8 - https://github.com/FasterXML/jackson-modules-java8
            "implementation"("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:${jacksonVersion}")
            "implementation"("com.fasterxml.jackson.module:jackson-module-parameter-names:${jacksonVersion}")
            "implementation"("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${jacksonVersion}")

            // Guice
            "implementation"("com.google.inject.extensions:guice-servlet:4.2.2")
            "implementation"("org.glassfish.hk2:guice-bridge:2.5.0")
            "implementation"("com.google.inject:guice:4.2.2") {
                // Resolving conflict - guice-servlet / guice-bridge
                isForce = true
            }

            // log4j
            "implementation"("org.apache.logging.log4j:log4j-api:${log4jVersion}")
            "implementation"("org.apache.logging.log4j:log4j-core:${log4jVersion}")
            // Required when running log4j in a web app. See: https://logging.apache.org/log4j/2.x/manual/webapp.html
            "implementation"("org.apache.logging.log4j:log4j-web:${log4jVersion}")
            // Required for async loggers: https://logging.apache.org/log4j/2.x/manual/async.html#Making_All_Loggers_Asynchronous
            "implementation"("com.lmax:disruptor:3.4.2")

            "implementation"("org.apache.commons:commons-lang3:3.9")

            // Immutables. See for using apt plugin to set up: https://github.com/tbroyer/gradle-apt-plugin#usage-with-ides
            "annotationProcessor"("org.immutables:value:${immutablesVersion}")
            "compileOnly"("org.immutables:value-annotations:${immutablesVersion}")

            // JUnit. See for setup: https://docs.gradle.org/current/userguide/java_testing.html#using_junit5
            "testImplementation"("org.junit.jupiter:junit-jupiter-api:${junitVersion}") {
                // Resolving conflict - this / testcontainers
                isForce = true
            }
            "testRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")

            // Hamcrest
            "testImplementation"("org.hamcrest:hamcrest-library:${hamcrestVersion}")
            "testImplementation"("org.hamcrest:hamcrest-core:${hamcrestVersion}") {
                // Only specified to resolve conflict - hamcrest-library / testcontainers
                isForce = true
            }

            // Mockito
            "testImplementation"("org.mockito:mockito-core:${mockitoVersion}")
            "testImplementation"("org.mockito:mockito-junit-jupiter:${mockitoVersion}")

            // TestContainers
            "testImplementation"("org.testcontainers:testcontainers:${testcontainersVersion}")
            "testImplementation"("org.testcontainers:junit-jupiter:${testcontainersVersion}")
            "testImplementation"("org.slf4j:slf4j-api:${sl4jVersion}") {
                // Only specified to resolve conflict between testcontainers libs.
                isForce = true
            }

            // Feign
            "testImplementation"("io.github.openfeign:feign-core:${feignVersion}")
            "testImplementation"("io.github.openfeign:feign-jackson:${feignVersion}")
            "testImplementation"("io.github.openfeign:feign-jaxrs:${feignVersion}")
            "testImplementation"("io.github.openfeign:feign-slf4j:${feignVersion}")
            // For redirecting Feign logging via SLF4j to log4j2. See: https://logging.apache.org/log4j/2.x/log4j-slf4j-impl/index.html
            "testImplementation"("org.apache.logging.log4j:log4j-slf4j-impl:${log4jVersion}")

            // Liquibase and Postgres
            "liquibaseRuntime"("org.liquibase:liquibase-core:3.8.1")
            "liquibaseRuntime"("org.postgresql:postgresql:42.2.13")
            "liquibaseRuntime"("org.slf4j:slf4j-api:${sl4jVersion}") {
                // Only specified to resolve conflicts when added.
                isForce = true
            }
            // Needed to fix class not found errors when run liquibase.
            "liquibaseRuntime"("javax.xml.bind:jaxb-api:${jaxbApiVersion}")
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