package com.jonjam.pinboard.service.location.svc.config;

import com.jonjam.pinboard.common.configuration.ConfigurationFactory;
import com.jonjam.pinboard.common.database.config.DatabaseConfiguration;
import com.jonjam.pinboard.common.database.config.DatabaseConfigurationFactory;
import com.jonjam.pinboard.common.service.config.GuiceConfiguration;
import com.jonjam.pinboard.common.service.config.GuiceConfigurationFactory;
import com.typesafe.config.Config;

public class LocationServiceConfigurationFactory extends ConfigurationFactory<LocationServiceConfiguration> {

    private final GuiceConfigurationFactory guiceConfigurationFactory;
    private final DatabaseConfigurationFactory databaseConfigurationFactory;

    public LocationServiceConfigurationFactory() {
        guiceConfigurationFactory = new GuiceConfigurationFactory();
        databaseConfigurationFactory = new DatabaseConfigurationFactory();
    }

    @Override
    public LocationServiceConfiguration getConfiguration(final Config config) {
        final GuiceConfiguration guiceConfiguration = guiceConfigurationFactory.getConfiguration(config);
        final DatabaseConfiguration databaseConfiguration = databaseConfigurationFactory.getConfiguration(config);

        return new LocationServiceConfiguration.Builder()
            .withDatabaseConfiguration(databaseConfiguration)
            .withGuiceConfiguration(guiceConfiguration)
            .build();
    }
}
