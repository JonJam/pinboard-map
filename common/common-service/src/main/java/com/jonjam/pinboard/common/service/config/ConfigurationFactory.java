package com.jonjam.pinboard.common.service.config;

import com.typesafe.config.Config;

public abstract class ConfigurationFactory<T> {
    public abstract T getConfiguration(final Config config);
}
