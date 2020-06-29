package com.jonjam.pinboard.common.service.config;

import com.jonjam.pinboard.common.objectmodel.Immutable;

@Immutable
public abstract class GuiceConfiguration {
    public abstract boolean useDevelopmentStage();

    public static class Builder extends ImmutableGuiceConfiguration.Builder { }
}
