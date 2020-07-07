package com.jonjam.pinboard.common.service.module;

import javax.sql.DataSource;

import com.google.inject.AbstractModule;
import com.google.inject.servlet.RequestScoped;
import com.jonjam.pinboard.common.database.AutoTransactionHandleContext;
import com.jonjam.pinboard.common.database.AutoTransactionHandleContextProvider;
import com.jonjam.pinboard.common.database.DataSourceProvider;
import com.jonjam.pinboard.common.database.JdbiProvider;
import com.jonjam.pinboard.common.database.config.DatabaseInfo;
import com.jonjam.pinboard.common.database.config.DatabaseInfoProvider;
import org.jdbi.v3.core.Jdbi;

public class DatabaseModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DatabaseInfo.class).toProvider(DatabaseInfoProvider.class).asEagerSingleton();
        bind(DataSource.class).toProvider(DataSourceProvider.class).asEagerSingleton();
        bind(Jdbi.class).toProvider(JdbiProvider.class).asEagerSingleton();

        bind(AutoTransactionHandleContext.class).toProvider(AutoTransactionHandleContextProvider.class).in(RequestScoped.class);
    }
}
