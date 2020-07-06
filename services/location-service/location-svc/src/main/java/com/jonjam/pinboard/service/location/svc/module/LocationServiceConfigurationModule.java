package com.jonjam.pinboard.service.location.svc.module;

import com.jonjam.pinboard.common.database.config.DatabaseConfiguration;
import com.jonjam.pinboard.common.service.module.ConfigurationModule;
import com.jonjam.pinboard.service.location.svc.config.LocationServiceConfiguration;

public class LocationServiceConfigurationModule extends ConfigurationModule<LocationServiceConfiguration> {

    public LocationServiceConfigurationModule(final LocationServiceConfiguration serviceConfig) {
        super(serviceConfig);
    }

    @Override
    protected void addAdditionalBinds(final LocationServiceConfiguration serviceConfig) {
        bind(LocationServiceConfiguration.class).toInstance(serviceConfig);
        bind(DatabaseConfiguration.class).toInstance(serviceConfig.getDatabaseConfiguration());
    }
}