package com.jonjam.pinboard.service.location.svc.controller;

import javax.inject.Inject;
import javax.ws.rs.Path;

import java.util.Optional;

import com.jonjam.pinboard.service.location.api.LocationService;
import com.jonjam.pinboard.service.location.api.model.CreateLocationRequest;
import com.jonjam.pinboard.service.location.api.model.LocationDto;
import com.jonjam.pinboard.service.location.api.model.LocationStatusDto;
import com.jonjam.pinboard.service.location.svc.dao.location.LocationDao;
import com.jonjam.pinboard.service.location.svc.dao.location.model.InsertLocationRequest;
import com.jonjam.pinboard.service.location.svc.dao.location.model.Location;
import com.jonjam.pinboard.service.location.svc.dao.location.model.LocationStatus;

@Path(LocationService.ROOT_PATH)
public class LocationController implements LocationService {

  private final LocationDao locationDao;

  @Inject
  public LocationController(final LocationDao locationDao) {
    this.locationDao = locationDao;
  }

  @Override
  public Optional<LocationDto> getLocation(final long locationCode) {
    final Optional<Location> location = locationDao.getByCode(locationCode);

    if (location.isEmpty()) {
      // TODO Verify this gets converted to null in Json?
      return Optional.empty();
    }

    return Optional.of(convertToLocationDto(location.get()));
  }

  @Override
  public LocationDto createLocation(final CreateLocationRequest request) {
    // TODO Replace with MapStruct conversions
    LocationStatus status;

    if (request.getStatus().equals(LocationStatusDto.ACTIVE)) {
      status = LocationStatus.ACTIVE;
    } else {
      throw new IllegalStateException();
    }

    final InsertLocationRequest dbRequest = new InsertLocationRequest.Builder()
        .withLocationStatus(status)
        .build();

    final Location createdLocation = locationDao.insertLocation(dbRequest);

    // TODO Remove - testing
    throw new IllegalStateException("should rollback insert");

//    return convertToLocationDto(createdLocation);
  }

  private LocationDto convertToLocationDto(final Location createdLocation) {
    LocationStatusDto statusDto;

    if (createdLocation.getLocationStatus().equals(LocationStatus.ACTIVE)) {
      statusDto = LocationStatusDto.ACTIVE;
    } else {
      throw new IllegalStateException();
    }

    return new LocationDto.Builder()
        .withCode(createdLocation.getLocationCode())
        .withStatus(statusDto)
        .build();
  }
}
