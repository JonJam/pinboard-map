package com.jonjam.pinboard.service.web.config;

import com.jonjam.pinboard.common.objectmodel.Immutable;
import com.jonjam.pinboard.common.service.config.ServiceClientConfiguration;

@Immutable
public abstract class WebServiceClientConfiguration implements ServiceClientConfiguration {
    public abstract String getLocation();

    public static class Builder extends ImmutableWebServiceClientConfiguration.Builder { }
}