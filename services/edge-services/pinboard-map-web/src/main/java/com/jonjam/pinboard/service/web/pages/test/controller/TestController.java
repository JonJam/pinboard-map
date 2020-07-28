package com.jonjam.pinboard.service.web.pages.test.controller;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jonjam.pinboard.service.web.pages.test.model.TestPageWebModel;

// TODO Remove
@Path("test")
public class TestController {

  @Inject
  public TestController() {
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public TestPageWebModel getTestPage() {
    return new TestPageWebModel.Builder()
        .withValue("Example")
        .build();
  }
}