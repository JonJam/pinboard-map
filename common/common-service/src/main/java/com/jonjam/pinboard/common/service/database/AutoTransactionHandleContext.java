package com.jonjam.pinboard.common.service.database;

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

    // TODO This should be called by whatever is executing the queries. ??
//
//    /**
//     * This method performs connection open/close count and responsible for closing connection
//     * (unless there is a transaction open at the moment)
//     */
//    public void closeConnection(Connection conn) {
//        if (this.currentConnection == null) {
//            LOGGER.warn()
//                  .write("current connection already closed");
//            return;
//        }
//
//        if (conn != this.currentConnection && conn != this.currentConnection.unwrapConnection()) {
//            throw new IllegalArgumentException("Don't know how to close this connection");
//        }
//        try {
//            if (this.currentConnection.getAutoCommit()) { // can eagerly release connection
//                releaseConnection();
//            } else {  // If in transaction - connection will be closed by transaction termination
//                LOG.debug().write("still in transaction - closing delegated to transaction handling code");
//            }
//        } catch (SQLException e) {
//            throw new DatabaseException(e);
//        }
//    }

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
