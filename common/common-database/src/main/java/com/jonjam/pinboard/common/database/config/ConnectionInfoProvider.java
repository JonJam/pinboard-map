package com.jonjam.pinboard.common.database.config;

import javax.inject.Inject;
import javax.inject.Provider;

public class ConnectionInfoProvider implements Provider<ConnectionInfo> {

    private DatabaseConfiguration databaseConfiguration;

    @Inject
    public ConnectionInfoProvider(final DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }

    @Override
    public ConnectionInfo get() {
        final String host = databaseConfiguration.getHost();
        final int port = databaseConfiguration.getPort();
        final String databaseName = databaseConfiguration.getDatabaseName();

        final int maxLifetime = databaseConfiguration.getMaxLifetime();
        final int maximumPoolSize = databaseConfiguration.getMaximumPoolSize();
        final int leakDetectionThreshold = databaseConfiguration.getLeakDetectionThreshold();

        final boolean useSSL = databaseConfiguration.getUseSSL();
        final String logLevel = databaseConfiguration.getLogLevel();
        final boolean logUnclosedConnections = databaseConfiguration.getLogUnclosedConnections();
        final int socketTimeout = databaseConfiguration.getSocketTimeout();

        final ConnectionInfo.Builder builder = new ConnectionInfo.Builder()
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

        if (databaseConfiguration.getUsername().isPresent() &&
            databaseConfiguration.getPassword().isPresent()) {
            builder.withUsername(databaseConfiguration.getUsername().get());
            builder.withPassword(databaseConfiguration.getPassword().get());
        } else {
            // TODO Implement vault support for credentials.
            throw new IllegalStateException("No database username and password specified.");
        }

        return builder.build();
    }
}
