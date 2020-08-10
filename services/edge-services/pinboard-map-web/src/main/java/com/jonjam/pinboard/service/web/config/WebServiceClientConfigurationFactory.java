package com.jonjam.pinboard.service.web.config;

import com.jonjam.pinboard.common.configuration.ConfigurationFactory;
import com.typesafe.config.Config;

public class WebServiceClientConfigurationFactory extends ConfigurationFactory<WebServiceClientConfiguration> {

    @Override
    public WebServiceClientConfiguration getConfiguration(final Config config) {

        final String location = config.getString("serviceMap.location");

        return new WebServiceClientConfiguration.Builder()
            .withLocation(location)
            .build();
    }
}
