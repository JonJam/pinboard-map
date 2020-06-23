package com.jonjam.pinboard.service.location.svc.config;

import com.jonjam.pinboard.common.service.config.ConfigurationFactory;
import com.jonjam.pinboard.common.service.config.SelfConfiguration;
import com.jonjam.pinboard.common.service.config.SelfConfigurationFactory;
import com.jonjam.pinboard.common.service.database.DatabaseConfiguration;
import com.jonjam.pinboard.common.service.database.DatabaseConfigurationFactory;
import com.typesafe.config.Config;

public class LocationConfigurationFactory extends ConfigurationFactory<LocationServiceConfiguration> {

    private final SelfConfigurationFactory selfConfigurationFactory;
    private final ServiceMapConfigurationFactory serviceMapConfigurationFactory;
    private final DatabaseConfigurationFactory databaseConfigurationFactory;

    public LocationConfigurationFactory() {
        selfConfigurationFactory = new SelfConfigurationFactory();
        serviceMapConfigurationFactory = new ServiceMapConfigurationFactory();
        databaseConfigurationFactory = new DatabaseConfigurationFactory();
    }

    @Override
    public LocationServiceConfiguration getConfiguration(final Config config) {
        final SelfConfiguration selfConfiguration = selfConfigurationFactory.getConfiguration(config);
        final ServiceMapConfiguration serviceMapConfiguration = serviceMapConfigurationFactory.getConfiguration(config);
        final DatabaseConfiguration databaseConfiguration = databaseConfigurationFactory.getConfiguration(config);

        return new LocationServiceConfiguration.Builder()
            .withDatabaseConfiguration(databaseConfiguration)
            .withSelfConfiguration(selfConfiguration)
            .withServiceMapConfiguration(serviceMapConfiguration)
            .build();
    }
}
