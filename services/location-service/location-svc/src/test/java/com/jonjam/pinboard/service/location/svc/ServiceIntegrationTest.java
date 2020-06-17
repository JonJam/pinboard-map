package com.jonjam.pinboard.service.location.svc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonjam.pinboard.common.objectmodel.ObjectMapperBuilder;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import feign.slf4j.Slf4jLogger;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

// TODO Move
@Testcontainers
public abstract class ServiceIntegrationTest {

  private static final Path DOCKER_BUILD_CONTEXT = Paths.get("..");
  private static final String APP_ID_BUILD_ARGUMENT_NAME = "APP_ID";
  private static final String TEST_APP_ID = "99";
  private static final String VERSION_BUILD_ARGUMENT_NAME = "VERSION";
  private static final String VERSION = "prod";

  // NOTE - last two digits map to TEST_APP_ID.
  private static final int HTTP_PORT = 8099;

  @Container
  private static GenericContainer<?> serviceContainer = new GenericContainer(new ImageFromDockerfile()
      .withFileFromPath(".", DOCKER_BUILD_CONTEXT)
      .withBuildArg(APP_ID_BUILD_ARGUMENT_NAME, TEST_APP_ID)
      .withBuildArg(VERSION_BUILD_ARGUMENT_NAME, VERSION)
  ).withExposedPorts(HTTP_PORT);

  protected <T> T getClient(final Class<T> serviceInterface) {
    final String address = "http://"
        + serviceContainer.getContainerIpAddress()
        + ":"
        + serviceContainer.getMappedPort(HTTP_PORT);

    final ObjectMapper objectMapper = ObjectMapperBuilder.build();

    return Feign.builder()
        .contract(new JAXRSContract())
        .encoder(new JacksonEncoder(objectMapper))
        .decoder(new JacksonDecoder(objectMapper))
        .logger(new Slf4jLogger(serviceInterface))
        .logLevel(Logger.Level.BASIC)
        .target(serviceInterface, address);
  }
}
