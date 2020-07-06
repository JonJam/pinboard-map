package com.jonjam.pinboard.common.database.config;

import java.util.Optional;

import com.jonjam.pinboard.common.objectmodel.Immutable;

@Immutable
public abstract class DriverConfiguration {
    public abstract String getDatabaseName();

    public abstract String getHost();

    public abstract Optional<String> getUsername();

    public abstract Optional<String> getPassword();

    public abstract int getPort();

    public abstract boolean getUseSSL();

    public abstract String getLogLevel();

    public abstract boolean getLogUnclosedConnections();

    public abstract int getSocketTimeout();

    public static class Builder extends ImmutableDriverConfiguration.Builder { }
}
