package com.jonjam.pinboard.common.service.database;

import javax.inject.Inject;

import java.util.function.Function;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Query;
import org.jdbi.v3.core.statement.SqlStatement;
import org.jdbi.v3.core.statement.Update;

public class HandleWrapper {

    private AutoTransactionHandleContext autoTransactionHandleContext;

    @Inject
    public HandleWrapper(final AutoTransactionHandleContext autoTransactionHandleContext) {
        this.autoTransactionHandleContext = autoTransactionHandleContext;
    }

    public Query createQuery(final String sql) {
        return autoTransactionHandleContext.getHandle()
                                           .createQuery(sql);
    }

    public Update createUpdate(final String sql) {
        return withinTransaction(h -> h.createUpdate(sql));
    }

    /**
     * Performs a SqlStatement with a transaction i.e. insert, update, delete.
     *
     * @param createSqlStatement A function to create a SQL statement.
     * @param <T> The type of SQL statement.
     * @return A SQL statement.
     */
    private <T extends SqlStatement<T>>  T  withinTransaction(
        final Function<Handle, T> createSqlStatement) {

        if (!autoTransactionHandleContext.isInTransaction()) {
            autoTransactionHandleContext.startTransaction();
        }

        try {
            return createSqlStatement.apply(autoTransactionHandleContext.getHandle());
        } catch (final RuntimeException e) {
            autoTransactionHandleContext.markRollback();
            throw e;
        }
    }
}
