package com.jonjam.pinboard.service.web.pages.location.controller;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jonjam.pinboard.service.location.api.ref.LocationCode;
import com.jonjam.pinboard.service.web.pages.location.model.CreateLocationWebRequest;
import com.jonjam.pinboard.service.web.pages.location.model.LocationWebModel;

// TODO Remove
@Path("location")
public class LocationController {

  @Inject
  public LocationController() {
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public LocationWebModel createLocation(@Valid final CreateLocationWebRequest request) {
    // TODO DO something

    return new LocationWebModel.Builder()
        .withCode(LocationCode.valueOf("1"))
        .build();
  }
}