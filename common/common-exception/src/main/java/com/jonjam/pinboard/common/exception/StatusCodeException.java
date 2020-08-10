package com.jonjam.pinboard.common.exception;

/**
 * Interface to be implemented by Exceptions that are associated with a particular HTTP status code.
 *
 * Not using javax.ws.rs.WebApplicationException as cannot be deserialized due to containing javax.ws.rs.Response field.
 */
public interface StatusCodeException {
    int statusCode();
}
