package com.jonjam.pinboard.service.location.svc.config;

import com.jonjam.pinboard.common.objectmodel.Immutable;
import com.jonjam.pinboard.service.location.svc.config.common.ServiceConfiguration;

@Immutable
public abstract class LocationServiceConfiguration extends ServiceConfiguration {
    public abstract ServiceMapConfiguration getServiceMapConfiguration();

    public static class Builder extends ImmutableLocationServiceConfiguration.Builder {}
}
