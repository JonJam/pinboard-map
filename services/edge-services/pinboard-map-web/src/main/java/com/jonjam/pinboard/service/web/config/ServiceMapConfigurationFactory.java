package com.jonjam.pinboard.service.web.config;

import com.jonjam.pinboard.common.configuration.ConfigurationFactory;
import com.typesafe.config.Config;

public class ServiceMapConfigurationFactory extends ConfigurationFactory<ServiceMapConfiguration> {

    @Override
    public ServiceMapConfiguration getConfiguration(final Config config) {

        final String location = config.getString("serviceMap.location");

        return new ServiceMapConfiguration.Builder()
            .withLocation(location)
            .build();
    }
}
