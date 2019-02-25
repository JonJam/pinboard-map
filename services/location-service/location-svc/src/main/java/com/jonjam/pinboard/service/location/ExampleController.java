package com.jonjam.pinboard.service.location;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path).
 */
@Path("example")
public class ExampleController {

  private final IInjectedService service;

  @Inject
  public ExampleController(
      final IInjectedService service) {
    this.service = service;
  }

  /**
   * Method handling HTTP GET requests.
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public ExampleResponse getIt() {
    ExampleResponse.Builder builder = new ExampleResponse.Builder();

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
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
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
