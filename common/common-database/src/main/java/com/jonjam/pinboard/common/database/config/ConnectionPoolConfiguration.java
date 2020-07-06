package com.jonjam.pinboard.common.database.config;

import com.jonjam.pinboard.common.objectmodel.Immutable;

@Immutable
public abstract class ConnectionPoolConfiguration {
    public abstract int getMaxLifetime();

    public abstract int getMaximumPoolSize();

    public abstract int getLeakDetectionThreshold();

    public static class Builder extends ImmutableConnectionPoolConfiguration.Builder { }
}
