package com.jonjam.pinboard.common.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jonjam.pinboard.common.database.AutoTransactionHandleContext;
import com.jonjam.pinboard.common.test.module.FakeRequestScopeModule;
import com.jonjam.pinboard.common.test.module.TestDatabaseModule;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

public class DatabaseIntegrationTest implements BeforeEachCallback, AfterEachCallback, TestInstancePostProcessor {

    private Injector injector;

    @Override
    public void beforeEach(final ExtensionContext context) {
        final AutoTransactionHandleContext transactionContext = injector.getInstance(AutoTransactionHandleContext.class);
        transactionContext.getHandle();

        // Start a transaction before each test, and mark it for rolling back at the end of the test.
        transactionContext.startTransaction();
        transactionContext.markRollback();
    }

    @Override
    public void afterEach(final ExtensionContext context) {
        injector.getInstance(AutoTransactionHandleContext.class).close();
    }

    @Override
    public void postProcessTestInstance(
        final Object testInstance,
        final ExtensionContext context) {
        injector = Guice.createInjector(new TestDatabaseModule(), new FakeRequestScopeModule());
        injector.injectMembers(testInstance);
    }
}
