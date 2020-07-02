package com.jonjam.pinboard.common.service.module;

import com.google.inject.AbstractModule;
import com.google.inject.servlet.RequestScoped;
import com.jonjam.pinboard.common.service.database.AutoTransactionHandleContext;
import com.jonjam.pinboard.common.service.database.AutoTransactionHandleContextProvider;
import com.jonjam.pinboard.common.service.database.ConnectionInfo;
import com.jonjam.pinboard.common.service.database.ConnectionInfoProvider;
import com.jonjam.pinboard.common.service.database.JdbiProvider;
import org.jdbi.v3.core.Jdbi;

public class DatabaseModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ConnectionInfo.class).toProvider(ConnectionInfoProvider.class).asEagerSingleton();
        bind(Jdbi.class).toProvider(JdbiProvider.class).asEagerSingleton();

        bind(AutoTransactionHandleContext.class).toProvider(AutoTransactionHandleContextProvider.class).in(RequestScoped.class);
    }
}
