package com.jonjam.pinboard.service.location.svc.config;

import com.jonjam.pinboard.common.configuration.ConfigurationFactory;
import com.jonjam.pinboard.common.database.config.DatabaseConfiguration;
import com.jonjam.pinboard.common.database.config.DatabaseConfigurationFactory;
import com.jonjam.pinboard.common.service.config.GuiceConfiguration;
import com.jonjam.pinboard.common.service.config.GuiceConfigurationFactory;
import com.jonjam.pinboard.common.service.config.SelfConfiguration;
import com.jonjam.pinboard.common.service.config.SelfConfigurationFactory;
import com.typesafe.config.Config;

public class LocationConfigurationFactory extends ConfigurationFactory<LocationServiceConfiguration> {

    private final SelfConfigurationFactory selfConfigurationFactory;
    private final GuiceConfigurationFactory guiceConfigurationFactory;
    private final ServiceMapConfigurationFactory serviceMapConfigurationFactory;
    private final DatabaseConfigurationFactory databaseConfigurationFactory;

    public LocationConfigurationFactory() {
        selfConfigurationFactory = new SelfConfigurationFactory();
        guiceConfigurationFactory = new GuiceConfigurationFactory();
        serviceMapConfigurationFactory = new ServiceMapConfigurationFactory();
        databaseConfigurationFactory = new DatabaseConfigurationFactory();
    }

    @Override
    public LocationServiceConfiguration getConfiguration(final Config config) {
        final SelfConfiguration selfConfiguration = selfConfigurationFactory.getConfiguration(config);
        final GuiceConfiguration guiceConfiguration = guiceConfigurationFactory.getConfiguration(config);
        final ServiceMapConfiguration serviceMapConfiguration = serviceMapConfigurationFactory.getConfiguration(config);
        final DatabaseConfiguration databaseConfiguration = databaseConfigurationFactory.getConfiguration(config);

        return new LocationServiceConfiguration.Builder()
            .withDatabaseConfiguration(databaseConfiguration)
            .withGuiceConfiguration(guiceConfiguration)
            .withSelfConfiguration(selfConfiguration)
            .withServiceMapConfiguration(serviceMapConfiguration)
            .build();
    }
}