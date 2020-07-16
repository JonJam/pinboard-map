package com.jonjam.pinboard.service.location.svc.controller;

import java.util.Optional;

import com.jonjam.pinboard.service.location.api.model.LocationDto;
import com.jonjam.pinboard.service.location.api.model.LocationStatusDto;
import com.jonjam.pinboard.service.location.svc.dao.location.LocationDao;
import com.jonjam.pinboard.service.location.svc.dao.location.model.Location;
import com.jonjam.pinboard.service.location.svc.dao.location.model.LocationStatus;
import com.jonjam.pinboard.service.location.svc.mapper.LocationMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationControllerTest {
  @Mock
  private LocationDao locationDao;

  @Mock
  private LocationMapper locationMapper;

  @InjectMocks
  private LocationController controller;

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

    when(locationMapper.map(any(Location.class))).thenAnswer(a -> {
      final Location loc = a.getArgument(0);
      return new LocationDto.Builder()
          .withCode(loc.getLocationCode())
          .withStatus(LocationStatusDto.valueOf(loc.getLocationStatus().name()))
          .build();
    });

    // ACT
    final Optional<LocationDto> location = controller.getLocation(locationCode);

    assertThat(location.orElseThrow().getCode(), is(locationCode));
    assertThat(location.orElseThrow().getStatus(), is(LocationStatusDto.ACTIVE));
  }
}