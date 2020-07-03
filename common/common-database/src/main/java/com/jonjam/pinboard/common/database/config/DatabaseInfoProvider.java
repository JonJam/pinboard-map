package com.jonjam.pinboard.common.database.config;

import javax.inject.Inject;
import javax.inject.Provider;

public class DatabaseInfoProvider implements Provider<DatabaseInfo> {

    private DatabaseConfiguration databaseConfiguration;

    @Inject
    public DatabaseInfoProvider(final DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }

    @Override
    public DatabaseInfo get() {
        final int maxLifetime = databaseConfiguration.getConnectionPoolConfiguration().getMaxLifetime();
        final int maximumPoolSize = databaseConfiguration.getConnectionPoolConfiguration().getMaximumPoolSize();
        final int leakDetectionThreshold = databaseConfiguration.getConnectionPoolConfiguration().getLeakDetectionThreshold();

        final String host = databaseConfiguration.getDriverConfiguration().getHost();
        final int port = databaseConfiguration.getDriverConfiguration().getPort();
        final String databaseName = databaseConfiguration.getDriverConfiguration().getDatabaseName();
        final boolean useSSL = databaseConfiguration.getDriverConfiguration().getUseSSL();
        final String logLevel = databaseConfiguration.getDriverConfiguration().getLogLevel();
        final boolean logUnclosedConnections = databaseConfiguration.getDriverConfiguration().getLogUnclosedConnections();
        final int socketTimeout = databaseConfiguration.getDriverConfiguration().getSocketTimeout();

        final DatabaseInfo.Builder builder = new DatabaseInfo.Builder()
            .withHost(host)
            .withPort(port)
            .withDatabaseName(databaseName)
            .withMaxLifetime(maxLifetime)
            .withMaximumPoolSize(maximumPoolSize)
            .withLeakDetectionThreshold(leakDetectionThreshold)
            .withUseSSL(useSSL)
            .withLogLevel(logLevel)
            .withLogUnclosedConnections(logUnclosedConnections)
            .withSocketTimeout(socketTimeout);

        if (databaseConfiguration.getDriverConfiguration().getUsername().isPresent() &&
            databaseConfiguration.getDriverConfiguration().getPassword().isPresent()) {
            builder.withUsername(databaseConfiguration.getDriverConfiguration().getUsername().get());
            builder.withPassword(databaseConfiguration.getDriverConfiguration().getPassword().get());
        } else {
            // TODO Implement vault support for credentials.
            throw new IllegalStateException("No database username and password specified.");
        }

        return builder.build();
    }
}
