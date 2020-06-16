package com.jonjam.pinboard.service.location.svc.config.common;

import com.jonjam.pinboard.common.objectmodel.Immutable;

@Immutable
public abstract class SelfConfiguration {
    public abstract String getRole();

    public static class Builder extends ImmutableSelfConfiguration.Builder {}
}
