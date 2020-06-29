package com.jonjam.pinboard.common.service.config;

public abstract class ServiceConfiguration {
    public abstract SelfConfiguration getSelfConfiguration();
    public abstract GuiceConfiguration getGuiceConfiguration();
}
