package com.jonjam.pinboard.service.web.pages.location.controller;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jonjam.pinboard.service.location.api.LocationService;
import com.jonjam.pinboard.service.location.api.model.CreateLocationRequest;
import com.jonjam.pinboard.service.location.api.model.LocationDto;
import com.jonjam.pinboard.service.web.pages.location.mapper.LocationWebMapper;
import com.jonjam.pinboard.service.web.pages.location.model.CreateLocationWebRequest;
import com.jonjam.pinboard.service.web.pages.location.model.LocationWebModel;

@Path("/web/location")
public class LocationWebController {

  private final LocationService locationService;

  private final LocationWebMapper locationMapper;

  @Inject
  public LocationWebController(
      final LocationService locationService,
      final LocationWebMapper locationMapper) {
    this.locationService = locationService;
    this.locationMapper = locationMapper;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public LocationWebModel createLocation(@Valid final CreateLocationWebRequest request) {
    final CreateLocationRequest createLocationRequest = locationMapper.map(request);

    final LocationDto createdLocation = locationService.createLocation(createLocationRequest);

    return locationMapper.map(createdLocation);
  }
}