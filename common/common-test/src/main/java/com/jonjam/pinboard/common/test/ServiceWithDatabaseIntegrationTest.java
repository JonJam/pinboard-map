package com.jonjam.pinboard.common.test;

import com.jonjam.pinboard.common.database.config.DatabaseInfo;
import com.jonjam.pinboard.common.test.container.PostgresTestContainer;
import com.jonjam.pinboard.common.test.container.ServiceTestContainer;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ServiceWithDatabaseIntegrationTest implements BeforeAllCallback {

    @Override
    public void beforeAll(final ExtensionContext context) {
        final String networkAlias = PostgresTestContainer.getNetworkAlias();
        final DatabaseInfo databaseInfo = PostgresTestContainer.getDatabaseInfo();

        ServiceTestContainer.start(networkAlias, databaseInfo);
    }
}
