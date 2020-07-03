package com.jonjam.pinboard.service.location.svc.config;

import com.jonjam.pinboard.common.configuration.ConfigurationFactory;
import com.typesafe.config.Config;

public class ServiceMapConfigurationFactory extends ConfigurationFactory<ServiceMapConfiguration> {

    @Override
    public ServiceMapConfiguration getConfiguration(final Config config) {

        final String test = config.getString("serviceMap.test");

        return new ServiceMapConfiguration.Builder()
            .withTest(test)
            .build();
    }
}
