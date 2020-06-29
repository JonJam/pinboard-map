package com.jonjam.pinboard.common.service.config;

import com.typesafe.config.Config;

public class GuiceConfigurationFactory extends ConfigurationFactory<GuiceConfiguration> {

    @Override
    public GuiceConfiguration getConfiguration(final Config config) {
        final boolean useDevelopmentStage = config.getBoolean("guice.useDevelopmentStage");

        return new GuiceConfiguration.Builder()
            .withUseDevelopmentStage(useDevelopmentStage)
            .build();
    }
}
