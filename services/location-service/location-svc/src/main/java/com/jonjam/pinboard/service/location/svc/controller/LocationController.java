package com.jonjam.pinboard.service.location.svc.controller;

import javax.inject.Inject;
import javax.ws.rs.Path;

import java.util.Optional;

import com.jonjam.pinboard.service.location.api.LocationService;
import com.jonjam.pinboard.service.location.api.model.CreateLocationRequest;
import com.jonjam.pinboard.service.location.api.model.LocationDto;
import com.jonjam.pinboard.service.location.api.ref.LocationCode;
import com.jonjam.pinboard.service.location.svc.dao.location.LocationDao;
import com.jonjam.pinboard.service.location.svc.dao.location.model.InsertLocationRequest;
import com.jonjam.pinboard.service.location.svc.dao.location.model.Location;
import com.jonjam.pinboard.service.location.svc.mapper.LocationMapper;

@Path(LocationService.ROOT_PATH)
public class LocationController implements LocationService {

  private final LocationDao locationDao;

  private final LocationMapper locationMapper;

  @Inject
  public LocationController(
      final LocationDao locationDao,
      final LocationMapper locationMapper) {
    this.locationDao = locationDao;
    this.locationMapper = locationMapper;
  }

  @Override
  public Optional<LocationDto> getLocation(final LocationCode locationCode) {
    return locationDao.getByCode(locationCode)
                      .map(locationMapper::map);
  }

  @Override
  public LocationDto createLocation(final CreateLocationRequest request) {
    final InsertLocationRequest dbRequest = locationMapper.map(request);

    final Location createdLocation = locationDao.insertLocation(dbRequest);

    return locationMapper.map(createdLocation);
  }
}
