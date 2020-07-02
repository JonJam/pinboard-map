package com.jonjam.pinboard.common.service.database;

import javax.inject.Inject;

import java.util.Optional;

import com.google.inject.Provider;
import org.jdbi.v3.core.Jdbi;

public class AutoTransactionHandleContextProvider implements Provider<AutoTransactionHandleContext> {

    private final Jdbi jdbi;

    @Inject
    public AutoTransactionHandleContextProvider(final Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public AutoTransactionHandleContext get() {
        final Optional<AutoTransactionHandleContext> context = AutoTransactionHandleContext.get();

        return context.orElseGet(
            () -> AutoTransactionHandleContext.create(jdbi)
        );
    }
}