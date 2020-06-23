package com.jonjam.pinboard.common.service.database;

import javax.inject.Inject;
import javax.inject.Provider;

public class ConnectionInfoProvider implements Provider<ConnectionInfo> {

    private DatabaseConfiguration databaseConfiguration;

    // TODO get credentials from vault
    @Inject
    public ConnectionInfoProvider(final DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }

    @Override
    public ConnectionInfo get() {
        // TODO Populate
        final String dbUser = "";
        final String dbPassword = "";

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

        return new ConnectionInfo.Builder()
            .withHost(host)
            .withPort(port)
            .withDatabaseName(databaseName)
            .withUsername(dbUser)
            .withPassword(dbPassword)
            .withMaxLifetime(maxLifetime)
            .withMaximumPoolSize(maximumPoolSize)
            .withLeakDetectionThreshold(leakDetectionThreshold)
            .withUseSSL(useSSL)
            .withLogLevel(logLevel)
            .withLogUnclosedConnections(logUnclosedConnections)
            .withSocketTimeout(socketTimeout)
            .build();
    }
}
