package com.jonjam.pinboard.service.location.svc.config;

import com.jonjam.pinboard.common.objectmodel.Immutable;

@Immutable
public abstract class ServiceMapConfiguration {
    public abstract String getTest();

    public static class Builder extends ImmutableServiceMapConfiguration.Builder {}
}
