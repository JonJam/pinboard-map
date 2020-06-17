package com.jonjam.pinboard.service.location.svc.controller;

import com.jonjam.pinboard.service.location.svc.service.IInjectedService;
import com.jonjam.pinboard.common.logging.StructuredLogger;
import com.jonjam.pinboard.service.location.api.ExampleService;
import com.jonjam.pinboard.service.location.api.model.ExampleRequest;
import com.jonjam.pinboard.service.location.api.model.ExampleResponse;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 * Implementation of ExampleService.
 */
@Path(ExampleService.ROOT_PATH)
public class ExampleController implements ExampleService {

  private static final StructuredLogger LOGGER = StructuredLogger.getLogger(ExampleController.class);

  private final IInjectedService service;

  @Inject
  public ExampleController(final IInjectedService service) {
    this.service = service;
  }

  /**
   * Method handling HTTP GET requests.
   */
  public ExampleResponse getIt() {
    LOGGER.error()
        .write("Calling getIt.");

    final ExampleResponse response = new ExampleResponse.Builder()
        .withBar(this.service.test().getProp())
        .withCanValidProperty(true)
        .withHasValidProp(true)
        .withHasValidProperty(true)
        .withIsValidProperty(true)
        .withShouldValidProperty(true)
        .build();

    LOGGER.info()
        .withData("response", response)
        .write("Built example response.");

    return response;
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
