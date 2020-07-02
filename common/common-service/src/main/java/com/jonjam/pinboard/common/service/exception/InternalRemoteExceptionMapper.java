package com.jonjam.pinboard.common.service.exception;

import javax.inject.Inject;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * FOR INTERNAL USE ONLY - serializes an exception thrown to JSON including stack trace information.
 */
public class InternalRemoteExceptionMapper extends AbstractRemoteExceptionMapper {

    @Inject
    public InternalRemoteExceptionMapper(final ObjectMapper objectMapper) {
        super(objectMapper); 
    }

    @Override
    protected ObjectMapper overrideObjectMapper(final ObjectMapper objectMapper) {
        return objectMapper.copy()
                           .addMixIn(Throwable.class, ThrowableMixin.class);
    }

    private static class ThrowableMixin {
        @JsonSerialize(using = ThrowableCauseSerializer.class)
        private Throwable cause;

        @JsonIgnore
        private String getLocalizedMessage() {
            return null;
        }

        @JsonIgnore
        public Throwable[] getSuppressed() {
            return new Throwable[0];
        }

        @JsonProperty("message")
        private String detailMessage;
    }

    private static class ThrowableCauseSerializer extends JsonSerializer<Throwable> {
        @Override
        public void serialize(
            final Throwable throwable,
            final JsonGenerator generator,
            final SerializerProvider serializers) throws IOException {

            generator.writeStartObject();

            generator.writeFieldName("message");
            // prepending error message with error type
            generator.writeString(throwable.getClass().getName() + ": " + throwable.getMessage());

            generator.writeFieldName("cause");
            final Throwable cause = throwable.getCause();
            if (cause != null && cause != throwable) {
                serialize(cause, generator, serializers);
            } else {
                generator.writeObject(null);
            }

            generator.writeFieldName("stackTrace");
            generator.writeObject(throwable.getStackTrace());

            generator.writeEndObject();
        }
    }
}
