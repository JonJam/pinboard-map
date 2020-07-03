package com.jonjam.pinboard.common.service.module;

import com.google.inject.AbstractModule;
import com.jonjam.pinboard.common.service.config.GuiceConfiguration;
import com.jonjam.pinboard.common.service.config.ServiceConfiguration;

public class ConfigurationModule<T extends ServiceConfiguration> extends AbstractModule {

    private final T serviceConfig;

    public ConfigurationModule(final T serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    @Override
    protected void configure() {
        bind(ServiceConfiguration.class).toInstance(serviceConfig);
        bind(GuiceConfiguration.class).toInstance(serviceConfig.getGuiceConfiguration());

        addAdditionalBinds(serviceConfig);
    }

    protected void addAdditionalBinds(final T serviceConfig) { }
}