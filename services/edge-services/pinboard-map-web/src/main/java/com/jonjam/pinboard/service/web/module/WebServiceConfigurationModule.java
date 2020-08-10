package com.jonjam.pinboard.service.web.module;

import com.jonjam.pinboard.common.service.module.ConfigurationModule;
import com.jonjam.pinboard.service.web.config.WebServiceConfiguration;

public class WebServiceConfigurationModule extends ConfigurationModule<WebServiceConfiguration> {

    public WebServiceConfigurationModule(final WebServiceConfiguration serviceConfig) {
        super(serviceConfig);
    }

    @Override
    protected void addAdditionalBinds(final com.jonjam.pinboard.service.web.config.WebServiceConfiguration serviceConfig) {
        bind(WebServiceConfiguration.class).toInstance(serviceConfig);
    }
}