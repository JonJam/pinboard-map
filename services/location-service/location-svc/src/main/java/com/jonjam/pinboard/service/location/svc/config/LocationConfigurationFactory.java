package com.jonjam.pinboard.service.location.svc.config;

import com.jonjam.pinboard.service.location.svc.config.common.ConfigurationFactory;
import com.jonjam.pinboard.service.location.svc.config.common.SelfConfiguration;
import com.jonjam.pinboard.service.location.svc.config.common.SelfConfigurationFactory;
import com.typesafe.config.Config;

public class LocationConfigurationFactory extends ConfigurationFactory<LocationServiceConfiguration> {

    private final SelfConfigurationFactory selfConfigurationFactory;
    private final ServiceMapConfigurationFactory serviceMapConfigurationFactory;

    public LocationConfigurationFactory() {
        selfConfigurationFactory = new SelfConfigurationFactory();
        serviceMapConfigurationFactory = new ServiceMapConfigurationFactory();
    }

    @Override
    public LocationServiceConfiguration getConfiguration(final Config config) {
        final SelfConfiguration selfConfiguration = selfConfigurationFactory.getConfiguration(config);
        final ServiceMapConfiguration serviceMapConfiguration = serviceMapConfigurationFactory.getConfiguration(config);

        return new LocationServiceConfiguration.Builder()
            .withSelfConfiguration(selfConfiguration)
            .withServiceMapConfiguration(serviceMapConfiguration)
            .build();
    }
}
