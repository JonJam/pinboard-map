package com.jonjam.pinboard.service.location;

//import javax.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path).
 */
@Path("myresource")
public class MyResource {

  //  @Inject
  //  private IInjectedService service;

  /**
   * Method handling HTTP GET requests. The returned object will be sent
   * to the client as "text/plain" media type.
   *
   * @return String that will be returned as a text/plain response.
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Test getIt() {
    // TODO Re-enable Guice
    // return service.test();
    return new Test("hi");
  }

  private class Test {

    private String prop;

    public Test() {

    }

    public Test(String prop) {
      this.prop = prop;
    }

    public void setProp(String prop) {
      this.prop = prop;
    }

    public String getProp() {
      return this.prop;
    }
  }
}
