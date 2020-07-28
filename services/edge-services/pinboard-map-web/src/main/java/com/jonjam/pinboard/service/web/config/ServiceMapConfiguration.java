package com.jonjam.pinboard.service.web.config;

import com.jonjam.pinboard.common.objectmodel.Immutable;

@Immutable
public abstract class ServiceMapConfiguration {
    public abstract String getLocation();

    public static class Builder extends ImmutableServiceMapConfiguration.Builder { }
}
