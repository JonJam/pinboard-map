package com.jonjam.pinboard.common.service.module;

import com.google.inject.AbstractModule;
import com.jonjam.pinboard.common.service.database.ConnectionInfo;
import com.jonjam.pinboard.common.service.database.ConnectionInfoProvider;
import com.jonjam.pinboard.common.service.database.JdbiProvider;
import org.jdbi.v3.core.Jdbi;

public class DatabaseModule extends AbstractModule {

    @Override
    protected void configure() {
        // TODO Binds here
        bind(ConnectionInfo.class).toProvider(ConnectionInfoProvider.class).asEagerSingleton();
        bind(Jdbi.class).toProvider(JdbiProvider.class).asEagerSingleton();

        // TODO transaction stuff

    }
}
