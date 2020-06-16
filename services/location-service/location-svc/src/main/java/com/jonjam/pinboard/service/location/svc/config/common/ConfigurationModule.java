package com.jonjam.pinboard.service.location.svc.config.common;

import com.google.inject.AbstractModule;

public class ConfigurationModule<T extends ServiceConfiguration> extends AbstractModule {

    private final T serviceConfig;

    public ConfigurationModule(final T serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    @Override
    protected void configure() {
        bind(ServiceConfiguration.class).toInstance(serviceConfig);
        bind(SelfConfiguration.class).toInstance(serviceConfig.getSelfConfiguration());

        addAdditionalBinds(serviceConfig);
    }

    protected void addAdditionalBinds(final T serviceConfig) {}
}