package com.jonjam.pinboard.service.web.config;

import com.jonjam.pinboard.common.configuration.ConfigurationFactory;
import com.jonjam.pinboard.common.service.config.GuiceConfiguration;
import com.jonjam.pinboard.common.service.config.GuiceConfigurationFactory;
import com.typesafe.config.Config;

public class WebServiceConfigurationFactory extends ConfigurationFactory<WebServiceConfiguration> {

    private final GuiceConfigurationFactory guiceConfigurationFactory;
    private final ServiceMapConfigurationFactory serviceMapConfigurationFactory;

    public WebServiceConfigurationFactory() {
        guiceConfigurationFactory = new GuiceConfigurationFactory();
        serviceMapConfigurationFactory = new ServiceMapConfigurationFactory();
    }

    @Override
    public WebServiceConfiguration getConfiguration(final Config config) {
        final GuiceConfiguration guiceConfiguration = guiceConfigurationFactory.getConfiguration(config);
        final ServiceMapConfiguration serviceMapConfiguration = serviceMapConfigurationFactory.getConfiguration(config);

        return new WebServiceConfiguration.Builder()
            .withGuiceConfiguration(guiceConfiguration)
            .withServiceMapConfiguration(serviceMapConfiguration)
            .build();
    }
}
