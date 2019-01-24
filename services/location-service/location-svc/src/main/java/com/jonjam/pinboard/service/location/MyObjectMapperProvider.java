package com.jonjam.pinboard.service.location;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Based off: https://github.com/jersey/jersey/blob/2.27/examples/json-jackson/src/main/java/org/glassfish/jersey/examples/jackson/MyObjectMapperProvider.java
 *
 * @author Jakub Podlesak (jakub.podlesak at oracle.com)
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