package com.jonjam.pinboard.common.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Exception thrown by services if an uncaught, "unexpected", exception is thrown by a service.
 *
 * Not using javax.ws.rs.WebApplicationException as cannot be deserialized due to containing javax.ws.rs.Response field.
 */
public class UncaughtServiceException extends RuntimeException implements StatusCodeException {

    @JsonCreator
    public UncaughtServiceException(@JsonProperty("message") final String message) {
        super(message);
    }

    public UncaughtServiceException(final Exception cause) {
        super(cause);
    }

    @Override
    public int statusCode() {
        return 500;
    }
}
