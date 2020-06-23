package com.jonjam.pinboard.common.service.database;

import javax.inject.Inject;
import javax.inject.Provider;

public class ConnectionPoolProvider implements Provider<ConnectionPool> {

    private final ConnectionInfo connectionInfo;

    @Inject
    public ConnectionPoolProvider(final ConnectionInfo connectionInfo) {
        this.connectionInfo = connectionInfo;
    }

    public ConnectionPool get() {
        return new ConnectionPool(connectionInfo);
    }
}
