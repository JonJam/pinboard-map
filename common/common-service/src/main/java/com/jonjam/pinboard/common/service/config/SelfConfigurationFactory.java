package com.jonjam.pinboard.common.service.config;

import com.jonjam.pinboard.common.configuration.ConfigurationFactory;
import com.typesafe.config.Config;

public class SelfConfigurationFactory extends ConfigurationFactory<SelfConfiguration> {

    @Override
    public SelfConfiguration getConfiguration(final Config config) {
        final String role = config.getString("self.role");

        return new SelfConfiguration.Builder()
            .withRole(role)
            .build();
    }
}
