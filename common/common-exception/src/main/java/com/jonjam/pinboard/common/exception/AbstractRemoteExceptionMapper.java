package com.jonjam.pinboard.common.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonjam.pinboard.common.logging.StructuredLogger;

public abstract class AbstractRemoteExceptionMapper implements ExceptionMapper<Exception> {

    private final StructuredLogger LOGGER = StructuredLogger.getLogger(getClass());

    private final ObjectMapper objectMapper;

    protected AbstractRemoteExceptionMapper(final ObjectMapper objectMapper) {
        this.objectMapper = overrideObjectMapper(objectMapper);
    }

    protected abstract ObjectMapper overrideObjectMapper(final ObjectMapper objectMapper);

    @Override
    public Response toResponse(final Exception e) {
        Exception exceptionToReturn = e;

        int statusCode;
        if (e instanceof WebApplicationException) {
            statusCode = ((WebApplicationException)e).getResponse()
                                                     .getStatus();
        } else if (e instanceof RuntimeException) {
            exceptionToReturn = new WebApplicationException(e);
            statusCode = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        }else {
            statusCode = Response.Status.BAD_REQUEST.getStatusCode();
        }

        if (statusCode >= Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
            LOGGER.error()
                  .withData("statusCode", statusCode)
                  .write("Unexpected exception.", e);
        } else {
            LOGGER.debug()
                  .withData("statusCode", statusCode)
                  .write("Service exception.", e);
        }

        final String exceptionType = exceptionToReturn.getClass()
                                                      .getName();
        final JsonNode exceptionDetails = objectMapper.valueToTree(exceptionToReturn);

        final RemoteExceptionDto body = new RemoteExceptionDto.Builder()
            .withExceptionType(exceptionType)
            .withExceptionDetails(exceptionDetails)
            .build();

        return Response.status(statusCode)
                       .type(MediaType.APPLICATION_JSON_TYPE)
                       .entity(body)
                       .build();
    }
}
