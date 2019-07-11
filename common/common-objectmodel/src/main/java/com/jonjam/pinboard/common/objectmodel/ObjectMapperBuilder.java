package com.jonjam.pinboard.common.objectmodel;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

public class ObjectMapperBuilder {

  private ObjectMapperBuilder() {
  }

  /**
   * Constructs a configured ObjectMapper.
   * @return Instance of ObjectMapper.
   */
  public static ObjectMapper build() {
    final ObjectMapper objectMapper = new ObjectMapper();

    return objectMapper
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true)
        .registerModule(new JavaTimeModule())
        .registerModule(new Jdk8Module())
        .registerModule(new ParameterNamesModule());
  }
}

