package com.jonjam.pinboard.common.database.config;

import com.jonjam.pinboard.common.configuration.ConfigurationFactory;
import com.typesafe.config.Config;

public class DatabaseConfigurationFactory extends ConfigurationFactory<DatabaseConfiguration> {

    @Override
    public DatabaseConfiguration getConfiguration(final Config config) {
        final String databaseName = config.getString("database.databaseName");
        final int port = config.getInt("database.port");
        final String host = config.getString("database.host");
        final int maxLifeTime = config.getInt("database.maxLifeTime");
        final int maximumPoolSize = config.getInt("database.maximumPoolSize");
        final int leakDetectionThreshold = config.getInt("database.leakDetectionThreshold");
        final boolean useSSL = config.getBoolean("database.useSSL");
        final String logLevel = config.getString("database.logLevel");
        final boolean logUnclosedConnections = config.getBoolean("database.logUnclosedConnections");
        final int socketTimeout = config.getInt("database.socketTimeout");

        final DatabaseConfiguration.Builder builder = new DatabaseConfiguration.Builder()
            .withDatabaseName(databaseName)
            .withPort(port)
            .withHost(host)
            .withMaxLifetime(maxLifeTime)
            .withMaximumPoolSize(maximumPoolSize)
            .withLeakDetectionThreshold(leakDetectionThreshold)
            .withUseSSL(useSSL)
            .withLogLevel(logLevel)
            .withLogUnclosedConnections(logUnclosedConnections)
            .withSocketTimeout(socketTimeout);

        // Optional support for specifying username and password via configuration.
        if (config.hasPath("database.username")) {
            builder.withUsername(config.getString("database.username"));
        }

        if (config.hasPath("database.password")) {
            builder.withPassword(config.getString("database.password"));
        }

        return builder.build();
    }
}
