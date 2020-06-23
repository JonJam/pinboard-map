package com.jonjam.pinboard.common.service.database;

import com.jonjam.pinboard.common.objectmodel.Immutable;

// TODO Split into driver vs pool
@Immutable
public abstract class DatabaseConfiguration {
    public abstract String getDatabaseName();

    public abstract String getHost();

    public abstract int getPort();

    public abstract int getMaxLifetime();

    public abstract int getMaximumPoolSize();

    public abstract int getLeakDetectionThreshold();

    public abstract boolean getUseSSL();

    public abstract String getLogLevel();

    public abstract boolean getLogUnclosedConnections();

    public abstract int getSocketTimeout();

    public static class Builder extends ImmutableDatabaseConfiguration.Builder { }
}
