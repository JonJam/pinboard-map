package com.jonjam.pinboard.service.location.svc.config.common;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.commons.lang3.StringUtils;

public class ConfigurationLoader {
    private static final String ENVIRONMENT_PROPERTY = "environment";

    public <T extends ServiceConfiguration> T readConfig(final ConfigurationFactory<T> configurationFactory) {
        final String environment = getEnvironment();

        final Config commonConfig = ConfigFactory.load("common");

        final Config environmentConfig = ConfigFactory.load(environment);

        final Config mergedConfig = environmentConfig.withFallback(commonConfig);

        // TODO override: host e.g. location-user
        // TODO override: system env

        return configurationFactory.getConfiguration(mergedConfig);
    }

    private String getEnvironment(){
        final String environment = System.getProperty(ENVIRONMENT_PROPERTY);

        if (StringUtils.isBlank(environment)) {
           throw new IllegalStateException(String.format("System property \"%s\" is not set.", ENVIRONMENT_PROPERTY));
        }

        return environment;
    }
}