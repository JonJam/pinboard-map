package com.jonjam.pinboard.service.web.module;

import java.util.Map;

import com.jonjam.pinboard.common.service.module.ServiceClientModule;
import com.jonjam.pinboard.service.location.api.LocationService;
import com.jonjam.pinboard.service.web.config.WebServiceClientConfiguration;

public class WebServiceClientModule extends ServiceClientModule<WebServiceClientConfiguration> {

    public WebServiceClientModule(final WebServiceClientConfiguration config) {
        super(config);
    }

    @Override
    protected Map<Class, String> getServiceClassToUrlMap(final WebServiceClientConfiguration config) {
        return Map.of(
            LocationService.class, config.getLocation()
        );
    }
}