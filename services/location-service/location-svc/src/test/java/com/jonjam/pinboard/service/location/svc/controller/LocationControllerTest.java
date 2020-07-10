package com.jonjam.pinboard.service.location.svc.controller;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import com.jonjam.pinboard.service.location.api.model.LocationDto;
import com.jonjam.pinboard.service.location.api.model.LocationStatusDto;
import com.jonjam.pinboard.service.location.svc.dao.location.LocationDao;
import com.jonjam.pinboard.service.location.svc.dao.location.model.Location;
import com.jonjam.pinboard.service.location.svc.dao.location.model.LocationStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LocationControllerTest {
  @Mock
  private LocationDao locationDao;

  @InjectMocks
  private LocationController exampleController;

  @Test
  void getLocation_existingLocation_returnsExpected() {
    // ARRANGE
    final long locationCode = 1;
    final Location locationFromDb = new Location.Builder()
        .withLocationCode(locationCode)
        .withLocationId(1)
        .withLocationStatus(LocationStatus.ACTIVE)
        .build();

    when(locationDao.getByCode(locationCode)).thenReturn(Optional.of(locationFromDb));

    // ACT
    final Optional<LocationDto> location = exampleController.getLocation(locationCode);

    assertThat(location.orElseThrow().getCode(), is(locationCode));
    assertThat(location.orElseThrow().getStatus(), is(LocationStatusDto.ACTIVE));
  }
}