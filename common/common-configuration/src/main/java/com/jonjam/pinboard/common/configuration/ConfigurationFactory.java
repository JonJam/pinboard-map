package com.jonjam.pinboard.common.configuration;

import com.typesafe.config.Config;

public abstract class ConfigurationFactory<T> {
    public abstract T getConfiguration(final Config config);
}
