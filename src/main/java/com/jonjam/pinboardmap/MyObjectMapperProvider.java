package com.jonjam.pinboardmap;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

/**
 * TODO javadoc.
 *
 * @author Jakub Podlesak (jakub.podlesak at oracle.com)
 *
 * Based off: https://github.com/jersey/jersey/blob/2.27/examples/json-jackson/src/main/java/org/glassfish/jersey/examples/jackson/MyObjectMapperProvider.java
 */
@Provider
public class MyObjectMapperProvider implements ContextResolver<ObjectMapper> {

    private final ObjectMapper defaultObjectMapper;

    public MyObjectMapperProvider() {
        this.defaultObjectMapper = createDefaultMapper();
    }

    @Override
    public ObjectMapper getContext(final Class<?> type) {
       return this.defaultObjectMapper;
    }

    private static ObjectMapper createDefaultMapper() {
        final ObjectMapper result = new ObjectMapper();
        result.enable(SerializationFeature.INDENT_OUTPUT);

        return result;
    }
}