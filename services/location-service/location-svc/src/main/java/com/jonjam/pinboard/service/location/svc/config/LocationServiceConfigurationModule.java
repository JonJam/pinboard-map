package com.jonjam.pinboard.service.location.svc.config;

import com.jonjam.pinboard.service.location.svc.config.common.ConfigurationModule;

public class LocationServiceConfigurationModule extends ConfigurationModule<LocationServiceConfiguration> {

    public LocationServiceConfigurationModule(final LocationServiceConfiguration serviceConfig) {
        super(serviceConfig);
    }

    @Override
    protected void addAdditionalBinds(final LocationServiceConfiguration serviceConfig) {
        bind(LocationServiceConfiguration.class).toInstance(serviceConfig);
    }
}