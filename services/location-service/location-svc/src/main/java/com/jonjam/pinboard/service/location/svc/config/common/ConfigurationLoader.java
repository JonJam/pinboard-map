package com.jonjam.pinboard.service.location.svc.config.common;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ConfigurationLoader {

    public <T extends ServiceConfiguration> T readConfig(final ConfigurationFactory<T> configurationFactory) {
        final Config config = ConfigFactory.load("common");

        // TODO override: environment e.g. dev.
        // TODO override: host e.g. location-user
        // TODO override: system env

        return configurationFactory.getConfiguration(config);
    }
}