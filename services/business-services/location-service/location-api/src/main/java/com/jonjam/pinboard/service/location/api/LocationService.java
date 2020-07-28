package com.jonjam.pinboard.service.location.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.Optional;

import com.jonjam.pinboard.service.location.api.model.CreateLocationRequest;
import com.jonjam.pinboard.service.location.api.model.LocationDto;
import com.jonjam.pinboard.service.location.api.ref.LocationCode;

@Path(LocationService.ROOT_PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface LocationService {

  String ROOT_PATH = "location";

  @GET
  @Path("/{locationCode}")
  Optional<LocationDto> getLocation(@PathParam("locationCode") LocationCode locationCode);

  @POST
  @Path("")
  LocationDto createLocation(CreateLocationRequest request);
}
