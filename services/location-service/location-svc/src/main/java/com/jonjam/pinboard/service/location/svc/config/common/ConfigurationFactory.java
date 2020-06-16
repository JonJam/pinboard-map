package com.jonjam.pinboard.service.location.svc.config.common;

import com.typesafe.config.Config;

public abstract class ConfigurationFactory<T> {
    public abstract T getConfiguration(final Config config);
}
