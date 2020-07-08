package com.jonjam.pinboard.common.test.container;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import com.jonjam.pinboard.common.database.DataSourceProvider;
import com.jonjam.pinboard.common.database.config.DatabaseInfo;
import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresTestContainer {

    // Creating singleton container as per: https://www.testcontainers.org/test_framework_integration/manual_lifecycle_control/#singleton-containers
    private static final PostgreSQLContainer<?> POSTGRES_CONTAINER;

    private static final String CHANGELOG_FILE = "changelog.xml";

    private static DatabaseInfo databaseInfo;
    private static DataSource dataSource;

    static {
        POSTGRES_CONTAINER = new PostgreSQLContainer<>();
        POSTGRES_CONTAINER.start();
        applyLiquibaseChangelog();
    }

    public static DatabaseInfo getDatabaseInfo() {
        if (databaseInfo == null) {
            databaseInfo = new DatabaseInfo.Builder()
                .withHost(POSTGRES_CONTAINER.getContainerIpAddress())
                .withPort(POSTGRES_CONTAINER.getFirstMappedPort())
                .withDatabaseName(POSTGRES_CONTAINER.getDatabaseName())
                .withMaxLifetime(600000)
                .withMaximumPoolSize(12)
                .withLeakDetectionThreshold(5000)
                .withUseSSL(false)
                .withLogLevel("OFF")
                .withLogUnclosedConnections(true)
                .withSocketTimeout(30)
                .withUsername(POSTGRES_CONTAINER.getUsername())
                .withPassword(POSTGRES_CONTAINER.getPassword())
                .build();
        }

        return databaseInfo;
    }

    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new DataSourceProvider(getDatabaseInfo()).get();
        }

        return dataSource;
    }

    private static void applyLiquibaseChangelog() {
        try (final Connection connection = getDataSource().getConnection()) {
            final Database database = DatabaseFactory.getInstance()
                                                     .findCorrectDatabaseImplementation(new JdbcConnection(connection));

            final Liquibase liquibase = new Liquibase(CHANGELOG_FILE, new ClassLoaderResourceAccessor(), database);

            liquibase.update(new Contexts());
        } catch (final SQLException | LiquibaseException e) {
            throw new RuntimeException(e);
        }
    }
}
