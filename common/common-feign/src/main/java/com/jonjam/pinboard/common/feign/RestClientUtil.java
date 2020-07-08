package com.jonjam.pinboard.common.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonjam.pinboard.common.objectmodel.ObjectMapperBuilder;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import feign.slf4j.Slf4jLogger;

public class RestClientUtil {

  public static <T> T createJAXRSClient(
      final Class<T> serviceInterface,
      final String url) {

    final ObjectMapper objectMapper = ObjectMapperBuilder.build();

    return Feign.builder()
        .contract(new JAXRSContract())
        .encoder(new JacksonEncoder(objectMapper))
        .decoder(new JacksonDecoder(objectMapper))
        .logger(new Slf4jLogger(serviceInterface))
        .logLevel(Logger.Level.FULL)
        .target(serviceInterface, url);
  }
}
