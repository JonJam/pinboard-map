package com.jonjam.pinboard.common.service.module;

import com.google.inject.AbstractModule;
import com.jonjam.pinboard.common.service.config.SelfConfiguration;
import com.jonjam.pinboard.common.service.config.ServiceConfiguration;

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

    protected void addAdditionalBinds(final T serviceConfig) { }
}