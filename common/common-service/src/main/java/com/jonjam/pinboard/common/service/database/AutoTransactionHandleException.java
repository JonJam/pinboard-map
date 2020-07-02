package com.jonjam.pinboard.common.service.database;

import org.jdbi.v3.core.JdbiException;

public class AutoTransactionHandleException extends JdbiException {

    public AutoTransactionHandleException(String message, Throwable cause) {
        super(message, cause);
    }

    public AutoTransactionHandleException(Throwable cause) {
        super(cause);
    }

    public AutoTransactionHandleException(String message) {
        super(message);
    }
}
