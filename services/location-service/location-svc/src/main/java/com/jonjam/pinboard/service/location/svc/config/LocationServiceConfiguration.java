package com.jonjam.pinboard.service.location.svc.config;

import com.jonjam.pinboard.common.database.config.DatabaseConfiguration;
import com.jonjam.pinboard.common.objectmodel.Immutable;
import com.jonjam.pinboard.common.service.config.ServiceConfiguration;

@Immutable
public abstract class LocationServiceConfiguration extends ServiceConfiguration {
    public abstract ServiceMapConfiguration getServiceMapConfiguration();

    public abstract DatabaseConfiguration getDatabaseConfiguration();

    public static class Builder extends ImmutableLocationServiceConfiguration.Builder { }
}
