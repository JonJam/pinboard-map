package com.jonjam.pinboard.service.location.svc.config.common;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.commons.lang3.StringUtils;

public class ConfigurationLoader<T extends ServiceConfiguration> {
    private static final String ENVIRONMENT = "ENVIRONMENT";
    private static final String HOSTNAME = "HOSTNAME";

    private static final String COMMON_CONFIGURATION_NAME = "common";

    private final ConfigurationFactory<T> configurationFactory;

    public ConfigurationLoader(final ConfigurationFactory<T> configurationFactory){
        this.configurationFactory = configurationFactory;
    }

    public T readConfig() {
        final String hostname = getEnvironmentVariable(HOSTNAME);
        final Config hostnameConfig = ConfigFactory.load(hostname);

        final String environment = getEnvironmentVariable(ENVIRONMENT);
        final Config environmentConfig = ConfigFactory.load(environment);

        final Config commonConfig = ConfigFactory.load(COMMON_CONFIGURATION_NAME);

        final Config mergedConfig = hostnameConfig.withFallback(environmentConfig)
                                                  .withFallback(commonConfig);

        return configurationFactory.getConfiguration(mergedConfig);
    }

    private String getEnvironmentVariable(final String environmentVariableName) {
        final String value = System.getenv(environmentVariableName);

        if (StringUtils.isBlank(value)) {
           throw new IllegalStateException(String.format("Environment variable \"%s\" is not set.", environmentVariableName));
        }

        return value;
    }
}