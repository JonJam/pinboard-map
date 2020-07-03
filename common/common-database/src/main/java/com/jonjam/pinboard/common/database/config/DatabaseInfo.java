package com.jonjam.pinboard.common.database.config;

import com.jonjam.pinboard.common.objectmodel.Immutable;

@Immutable
public abstract class DatabaseInfo {
    public abstract String getHost();
    public abstract int getPort();
    public abstract String getDatabaseName();
    public abstract String getUsername();
    public abstract String getPassword();

    public abstract int getMaxLifetime();
    public abstract int getMaximumPoolSize();
    public abstract int getLeakDetectionThreshold();

    public abstract boolean getUseSSL();
    public abstract String getLogLevel();
    public abstract boolean getLogUnclosedConnections();
    public abstract int getSocketTimeout();

    public static class Builder extends ImmutableDatabaseInfo.Builder { }
}
