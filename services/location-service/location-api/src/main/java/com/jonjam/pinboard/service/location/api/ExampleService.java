package com.jonjam.pinboard.service.location.api;

import com.jonjam.pinboard.service.location.api.model.ExampleRequest;
import com.jonjam.pinboard.service.location.api.model.ExampleResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("example")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ExampleService {

  /**
   * Method handling HTTP GET requests.
   */
  @GET
  @Path("")
  ExampleResponse getIt();

  /**
   * Method handling HTTP POST requests.
   */
  @POST
  @Path("")
  ExampleResponse postIt(final ExampleRequest request);
}
