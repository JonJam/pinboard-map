package com.jonjam.pinboard.service.location;

import javax.inject.Inject;
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

  @Inject
  private IInjectedService service;

  /**
   * Method handling HTTP GET requests. The returned object will be sent
   * to the client as "text/plain" media type.
   *
   * @return String that will be returned as a text/plain response.
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Test getIt() {
    return service.test();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public PostResponse postIt(PostRequest request) {
    PostResponse response = ImmutablePostResponse.builder()
        .withBar(request.getBar()).build();

    return response;
  }
}
