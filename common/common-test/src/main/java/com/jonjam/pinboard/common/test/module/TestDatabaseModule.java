package com.jonjam.pinboard.common.test.module;

import javax.sql.DataSource;

import com.google.inject.AbstractModule;
import com.google.inject.util.Modules;
import com.jonjam.pinboard.common.database.config.DatabaseInfo;
import com.jonjam.pinboard.common.service.module.DatabaseModule;
import com.jonjam.pinboard.common.test.container.PostgresTestContainer;

public class TestDatabaseModule extends AbstractModule {

    @Override
    protected void configure() {
        install(Modules.override(new DatabaseModule())
                       .with(new AbstractModule() {
                           @Override
                           protected void configure() {
                               bind(DatabaseInfo.class).toInstance(PostgresTestContainer.getDatabaseInfo());
                               bind(DataSource.class).toInstance(PostgresTestContainer.getDataSource());
                           }
                       }));
    }
}
