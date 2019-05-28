package com.jonjam.pinboard.service.location.integration;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
abstract class ServiceIntegrationTest {

  private static final Path dockerBuildContext = Paths.get("..");
  private static final String APP_ID_BUILD_ARGUMENT_NAME = "APP_ID";
  private static final String TEST_APP_ID = "99";

  // NOTE - last two digits map to TEST_APP_ID.
  private static final int HTTP_PORT = 8099;

  @Container
  private static GenericContainer serviceContainer = new GenericContainer(
      new ImageFromDockerfile()
          .withFileFromPath(".", dockerBuildContext)
          .withBuildArg(APP_ID_BUILD_ARGUMENT_NAME, TEST_APP_ID))
      .withExposedPorts(HTTP_PORT);

  protected <T> T getClient(Class<T> serviceInterface) {
    final String address = "http://"
        + serviceContainer.getContainerIpAddress()
        + ":"
        + serviceContainer.getMappedPort(HTTP_PORT);

    return Feign.builder()
        .contract(new JAXRSContract())
        .encoder(new JacksonEncoder())
        .decoder(new JacksonDecoder())
        .target(serviceInterface, address);
  }
}
