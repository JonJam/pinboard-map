package com.jonjam.pinboard.service.location.integration;

import com.jonjam.pinboard.service.location.ExampleController;
import com.jonjam.pinboard.service.location.ExampleResponse;
import com.jonjam.pinboard.service.location.IInjectedService;
import com.jonjam.pinboard.service.location.TestDto;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.containers.wait.strategy.WaitStrategy;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@Testcontainers
//@ExtendWith(ServiceTest.class)
class ExampleControllerIntegrationTest {

  // TODO Sort out this port
  private final static int PORT = 8031;

  // TODO switch to docker-compose ?
  // TODO Use @container
//  @Container
//  private GenericContainer dslContainer = new GenericContainer(
//        new ImageFromDockerfile()
//            .withFileFromPath("Dockerfile", FilePath."Dockerfile"));

  private static final Path dockerBuildContext = Paths.get("..");

  @Container
  private GenericContainer dslContainer = new GenericContainer(
      new ImageFromDockerfile()
          .withFileFromPath(".", dockerBuildContext))
      .withExposedPorts(PORT);

  @Test
  void getIt_returnsExampleResponse() {
    String address =
        dslContainer.getContainerIpAddress() + ":" + dslContainer.getMappedPort(PORT);

  // TODO Create FEIGN client here
//    Feign.builder()
//        .decoder(new GsonDecoder())
//        .target(GitHub.class, "https://api.github.com");

  }
}
