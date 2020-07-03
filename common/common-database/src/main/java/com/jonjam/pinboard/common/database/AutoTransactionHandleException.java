package com.jonjam.pinboard.common.database;

import org.jdbi.v3.core.JdbiException;

public class AutoTransactionHandleException extends JdbiException {

    public AutoTransactionHandleException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AutoTransactionHandleException(final Throwable cause) {
        super(cause);
    }

    public AutoTransactionHandleException(final String message) {
        super(message);
    }
}
