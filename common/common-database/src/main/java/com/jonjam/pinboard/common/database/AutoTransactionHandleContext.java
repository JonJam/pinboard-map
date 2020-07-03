package com.jonjam.pinboard.common.database;

import com.jonjam.pinboard.common.logging.StructuredLogger;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import java.sql.SQLException;
import java.util.Optional;

public class AutoTransactionHandleContext implements AutoCloseable {

    private static final StructuredLogger LOGGER = StructuredLogger.getLogger(AutoTransactionHandleContext.class);
    private static final ThreadLocal<AutoTransactionHandleContext> TRANSACTION_CONTEXT_THREAD_LOCAL = new ThreadLocal<>();

    private final Jdbi jdbi;
    private Handle currentHandle;
    private int transactionDepth = 0;
    private boolean isRollback = false;

    private AutoTransactionHandleContext(final Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    static AutoTransactionHandleContext create(final Jdbi jdbi) {
        AutoTransactionHandleContext context = TRANSACTION_CONTEXT_THREAD_LOCAL.get();

        if (context == null) {
            context = new AutoTransactionHandleContext(jdbi);
            TRANSACTION_CONTEXT_THREAD_LOCAL.set(context);
        } else {
            throw new AutoTransactionHandleException("Context already exists.");
        }

        return context;
    }

    static Optional<AutoTransactionHandleContext> get() {
        return Optional.ofNullable(TRANSACTION_CONTEXT_THREAD_LOCAL.get());
    }

    public Handle getHandle() {
        if (currentHandle == null) {

            Handle handle;

            try {
                handle = jdbi.open();

                if (isInTransaction()) {
                    disableAutoCommitOnConnection(handle);
                }
            } catch (final SQLException e) {
                throw new AutoTransactionHandleException(e);
            }

            currentHandle = handle;

            LOGGER.debug()
                  .write("Handle opened.");
        }

        return currentHandle;
    }


    public void startTransaction() {
        try {
            if (currentHandle != null) {
                disableAutoCommitOnConnection(currentHandle);
            }

            startLogicalTransaction();
        } catch (final SQLException e) {
            throw new AutoTransactionHandleException(e);
        }
    }

    public boolean isInTransaction() {
        return transactionDepth > 0;
    }

    public void markRollback() {
        isRollback = true;
    }

    @Override
    public void close() {
        try {
            if (currentHandle != null) {
                releaseConnection();
            }
        } finally {
            TRANSACTION_CONTEXT_THREAD_LOCAL.remove();
        }
    }

    private boolean isTopLevelTransaction() {
        return transactionDepth == 1;
    }

    private void startLogicalTransaction() {
        transactionDepth++;

        if (transactionDepth == 1) {
            LOGGER.debug().write("Logical transaction start.");
        } else {
            LOGGER.debug()
                  .withData("depth", transactionDepth)
                  .write("Nested logical transaction start.");
        }
    }

    private void closeLogicalTransaction() {
        if (transactionDepth == 1) {
            LOGGER.debug()
                  .write("Logical transaction closed.");
        } else {
            LOGGER.debug()
                  .withData("depth", transactionDepth)
                  .write("Nested logical transaction closed.");
        }

        transactionDepth--;
    }

    private void disableAutoCommitOnConnection(final Handle handle) throws SQLException {
        handle.getConnection()
              .setAutoCommit(false);
    }

    private void enableAutoCommitOnConnection(final Handle handle) throws SQLException {
        handle.getConnection()
              .setAutoCommit(true);
    }

    private void commit() {
        try {
            if (isTopLevelTransaction()) {
                if (isRollback) {
                    throw new AutoTransactionHandleException("Unable to commit transaction, transaction has been marked for rollback.");
                }

                currentHandle.commit();

                LOGGER.debug()
                      .write("Transaction commit.");

                enableAutoCommitOnConnection(currentHandle);
            } else {
                LOGGER.debug()
                      .withData("depth", transactionDepth)
                      .write("Nexted transaction commit.");
            }

            closeLogicalTransaction();
        } catch (final SQLException e) {
            throw new AutoTransactionHandleException(e);
        }
    }

    private void rollback() {
        markRollback();

        try {
            if (isTopLevelTransaction()) {
                currentHandle.rollback();

                enableAutoCommitOnConnection(currentHandle);

                isRollback = false;

                LOGGER.debug()
                      .write("Transaction rollback.");
            } else {
                LOGGER.debug()
                      .withData("depth", transactionDepth)
                      .write("Nested transaction rollback");
            }

            closeLogicalTransaction();
        } catch (final SQLException e) {
            throw new AutoTransactionHandleException(e);
        }
    }

    private void releaseConnection() {
        try {
            while (isInTransaction()) {
                if (isRollback) {
                    rollback();
                } else {
                    commit();
                }
            }
        } finally {
            currentHandle.close();

            currentHandle = null;

            LOGGER.debug()
                  .write("Handle closed.");
        }
    }
}
