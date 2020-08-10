package com.jonjam.pinboard.common.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.Logger;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import feign.slf4j.Slf4jLogger;

public class FeignBuilder {
    public static <T> T build(
        final ObjectMapper objectMapper,
        final Class<T> serviceInterface,
        final String url) {
        return createBuilderWithCommonSetup(objectMapper, serviceInterface)
            .target(serviceInterface, url);
    }

    public static <T> T buildWithErrorDecoder(
        final ObjectMapper objectMapper,
        final ErrorDecoder errorDecoder,
        final Class<T> serviceInterface,
        final String url) {
        return createBuilderWithCommonSetup(objectMapper, serviceInterface)
            .errorDecoder(errorDecoder)
            .target(serviceInterface, url);
    }

    private static <T> Feign.Builder createBuilderWithCommonSetup(
        final ObjectMapper objectMapper,
        final Class<T> serviceInterface) {
        return Feign.builder()
                    .contract(new JAXRSContract())
                    .encoder(new JacksonEncoder(objectMapper))
                    .decoder(new JacksonDecoder(objectMapper))
                    .logger(new Slf4jLogger(serviceInterface))
                    .logLevel(Logger.Level.FULL);
    }
}
