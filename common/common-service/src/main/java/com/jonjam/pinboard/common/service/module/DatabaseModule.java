package com.jonjam.pinboard.common.service.module;

import com.google.inject.AbstractModule;
import com.jonjam.pinboard.common.service.database.ConnectionInfo;
import com.jonjam.pinboard.common.service.database.ConnectionInfoProvider;
import com.jonjam.pinboard.common.service.database.ConnectionPool;
import com.jonjam.pinboard.common.service.database.ConnectionPoolProvider;

public class DatabaseModule extends AbstractModule {

    @Override
    protected void configure() {
        // TODO Binds here
        // TODO Need to create provider classes ?
        bind(ConnectionInfo.class).toProvider(ConnectionInfoProvider.class).asEagerSingleton();
        bind(ConnectionPool.class).toProvider(ConnectionPoolProvider.class).asEagerSingleton();

        // TODO transaction stuff

        // TODO jdbi
    }
}
