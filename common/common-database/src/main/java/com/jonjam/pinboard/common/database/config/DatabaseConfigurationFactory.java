package com.jonjam.pinboard.common.database.config;

import com.jonjam.pinboard.common.configuration.ConfigurationFactory;
import com.typesafe.config.Config;

public class DatabaseConfigurationFactory extends ConfigurationFactory<DatabaseConfiguration> {

    @Override
    public DatabaseConfiguration getConfiguration(final Config config) {
        final int maxLifeTime = config.getInt("database.connectionPool.maxLifeTime");
        final int maximumPoolSize = config.getInt("database.connectionPool.maximumPoolSize");
        final int leakDetectionThreshold = config.getInt("database.connectionPool.leakDetectionThreshold");

        final ConnectionPoolConfiguration poolConfiguration = new ConnectionPoolConfiguration.Builder()
            .withMaxLifetime(maxLifeTime)
            .withMaximumPoolSize(maximumPoolSize)
            .withLeakDetectionThreshold(leakDetectionThreshold)
            .build();

        final String databaseName = config.getString("database.driver.databaseName");
        final int port = config.getInt("database.driver.port");
        final String host = config.getString("database.driver.host");
        final boolean useSSL = config.getBoolean("database.driver.useSSL");
        final String logLevel = config.getString("database.driver.logLevel");
        final boolean logUnclosedConnections = config.getBoolean("database.driver.logUnclosedConnections");
        final int socketTimeout = config.getInt("database.driver.socketTimeout");

        final DriverConfiguration.Builder driverConfiguration = new DriverConfiguration.Builder()
            .withDatabaseName(databaseName)
            .withPort(port)
            .withHost(host)
            .withUseSSL(useSSL)
            .withLogLevel(logLevel)
            .withLogUnclosedConnections(logUnclosedConnections)
            .withSocketTimeout(socketTimeout);

        // Optional support for specifying username and password via configuration.
        if (config.hasPath("database.username")) {
            driverConfiguration.withUsername(config.getString("database.username"));
        }

        if (config.hasPath("database.password")) {
            driverConfiguration.withPassword(config.getString("database.password"));
        }

        return new DatabaseConfiguration.Builder()
            .withConnectionPoolConfiguration(poolConfiguration)
            .withDriverConfiguration(driverConfiguration.build())
            .build();
    }
}
