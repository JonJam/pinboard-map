package com.jonjam.pinboard.service.location;

import com.jonjam.pinboard.service.location.api.ExampleService;
import com.jonjam.pinboard.service.location.api.model.ExampleRequest;
import com.jonjam.pinboard.service.location.api.model.ExampleResponse;
import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation of ExampleService.
 */
public class ExampleController implements ExampleService {

  private static final Logger LOGGER = LogManager.getLogger();

  private final IInjectedService service;

  @Inject
  public ExampleController(
      final IInjectedService service) {
    this.service = service;
  }

  /**
   * Method handling HTTP GET requests.
   */
  public ExampleResponse getIt() {
    LOGGER.error("Calling getIt.");

    ExampleResponse.Builder builder = new ExampleResponse.Builder();

    LOGGER.info("Built example response.");

    return builder
        .withBar(this.service.test().getProp())
        .withCanValidProperty(true)
        .withHasValidProp(true)
        .withHasValidProperty(true)
        .withIsValidProperty(true)
        .withShouldValidProperty(true)
        .build();
  }

  /**
   * Method handling HTTP POST requests.
   */
  public ExampleResponse postIt(final ExampleRequest request) {
    ExampleResponse.Builder builder = new ExampleResponse.Builder();

    return builder
        .withBar(request.getBar())
        .withCanValidProperty(true)
        .withHasValidProp(true)
        .withHasValidProperty(true)
        .withIsValidProperty(true)
        .withShouldValidProperty(true)
        .build();
  }
}
