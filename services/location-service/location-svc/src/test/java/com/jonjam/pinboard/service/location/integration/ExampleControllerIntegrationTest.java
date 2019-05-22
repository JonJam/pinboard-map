package com.jonjam.pinboard.service.location.integration;

import com.jonjam.pinboard.service.location.api.ExampleService;
import com.jonjam.pinboard.service.location.api.model.ExampleResponse;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Testcontainers
class ExampleControllerIntegrationTest {

  // TODO Sort out this port
  // TODO switch to docker-compose ?
  // TODO Create base class
  private static final int PORT = 8031;
  private static final Path dockerBuildContext = Paths.get("..");

//  @Container
//  private GenericContainer dslContainer = new GenericContainer(
//      new ImageFromDockerfile()
//          .withFileFromPath(".", dockerBuildContext))
//      .withExposedPorts(PORT);

  @Test
  void getIt_returnsExampleResponse() {

    GenericContainer dslContainer = new GenericContainer(
        new ImageFromDockerfile()
            .withFileFromPath(".", dockerBuildContext))
        .withExposedPorts(PORT);

    dslContainer.start();

    final String address = "http://" +
        dslContainer.getContainerIpAddress() +
        ":" +
        dslContainer.getMappedPort(PORT);

      final ExampleService service = Feign.builder()
          .contract(new JAXRSContract())
          .encoder(new JacksonEncoder())
          .decoder(new JacksonDecoder())
          .target(ExampleService.class, address);

      final ExampleResponse response = service.getIt();
      assertThat(response.getHasValidProperty(), is(true));

    dslContainer.stop();
  }
}
