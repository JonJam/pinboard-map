package com.jonjam.pinboard.service.web.config;

import com.jonjam.pinboard.common.objectmodel.Immutable;
import com.jonjam.pinboard.common.service.config.ServiceConfiguration;

@Immutable
public abstract class WebServiceConfiguration extends ServiceConfiguration {
    public abstract ServiceMapConfiguration getServiceMapConfiguration();

    public static class Builder extends ImmutableWebServiceConfiguration.Builder { }
}
