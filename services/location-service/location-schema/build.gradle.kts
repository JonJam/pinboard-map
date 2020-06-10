plugins {
    id("org.liquibase.gradle")
}

dependencies {
    liquibaseRuntime("org.liquibase:liquibase-core")
    liquibaseRuntime("javax.xml.bind:jaxb-api")
    liquibaseRuntime("org.postgresql:postgresql")
}

liquibase {
    activities.register("main") {
        val dbUrl = project.property("schema_db_url")
        val dbUsername = project.property("schema_db_username")
        val dbPassword = project.property("schema_db_password")
        this.arguments = mapOf(
                "logLevel" to "info",
                "changeLogFile" to "src/main/db/changelog.xml",
                "url" to dbUrl,
                "username" to dbUsername,
                "password" to dbPassword
        )
    }
}