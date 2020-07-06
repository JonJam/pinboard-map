package com.jonjam.pinboard.common.database.config;

import com.jonjam.pinboard.common.objectmodel.Immutable;

@Immutable
public abstract class DatabaseConfiguration {
    public abstract ConnectionPoolConfiguration getConnectionPoolConfiguration();

    public abstract DriverConfiguration getDriverConfiguration();

    public static class Builder extends ImmutableDatabaseConfiguration.Builder { }
}
