package com.jonjam.pinboard.common.service.module;

import com.google.inject.AbstractModule;
import com.google.inject.servlet.RequestScoped;
import com.jonjam.pinboard.common.database.AutoTransactionHandleContext;
import com.jonjam.pinboard.common.database.AutoTransactionHandleContextProvider;
import com.jonjam.pinboard.common.database.JdbiProvider;
import com.jonjam.pinboard.common.database.config.ConnectionInfo;
import com.jonjam.pinboard.common.database.config.ConnectionInfoProvider;
import org.jdbi.v3.core.Jdbi;

public class DatabaseModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ConnectionInfo.class).toProvider(ConnectionInfoProvider.class).asEagerSingleton();
        bind(Jdbi.class).toProvider(JdbiProvider.class).asEagerSingleton();

        bind(AutoTransactionHandleContext.class).toProvider(AutoTransactionHandleContextProvider.class).in(RequestScoped.class);
    }
}
