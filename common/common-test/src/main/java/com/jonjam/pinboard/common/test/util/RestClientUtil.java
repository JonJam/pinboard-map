package com.jonjam.pinboard.common.test.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonjam.pinboard.common.feign.FeignBuilder;
import com.jonjam.pinboard.common.objectmodel.ObjectMapperBuilder;

public class RestClientUtil {

  public static <T> T createJAXRSClient(
      final Class<T> serviceInterface,
      final String url) {

    final ObjectMapper objectMapper = ObjectMapperBuilder.build();

    return FeignBuilder.build(objectMapper, serviceInterface, url);
  }
}
