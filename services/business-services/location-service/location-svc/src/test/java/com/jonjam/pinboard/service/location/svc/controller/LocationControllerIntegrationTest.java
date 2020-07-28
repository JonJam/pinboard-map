package com.jonjam.pinboard.service.location.svc.controller;

import java.util.Optional;

import com.jonjam.pinboard.common.feign.RestClientUtil;
import com.jonjam.pinboard.common.test.ServiceWithDatabaseIntegrationTest;
import com.jonjam.pinboard.common.test.container.ServiceTestContainer;
import com.jonjam.pinboard.service.location.api.LocationService;
import com.jonjam.pinboard.service.location.api.model.CreateLocationRequest;
import com.jonjam.pinboard.service.location.api.model.LocationDto;
import com.jonjam.pinboard.service.location.api.model.LocationStatusDto;
import com.jonjam.pinboard.service.location.api.ref.LocationCode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(ServiceWithDatabaseIntegrationTest.class)
class LocationControllerIntegrationTest {
    private static LocationService client;

    @BeforeAll
    static void beforeAll() {
      client = RestClientUtil.createJAXRSClient(LocationService.class, ServiceTestContainer.getUrl());
    }

    @Test
    void getLocation_noLocation_returnsEmpty() {
        // ARRANGE
        final LocationCode locationCode = LocationCode.valueOf(1);

        // ACT
        final Optional<LocationDto> location = client.getLocation(locationCode);

        // ASSERT
        assertThat(location.isEmpty(), is(true));
    }

    @Test
    void getLocation_existingLocation_returnsExpectedLocation() {
        // ARRANGE
        final LocationStatusDto expectedStatus = LocationStatusDto.ACTIVE;
        final CreateLocationRequest request = new CreateLocationRequest.Builder()
            .withStatus(expectedStatus)
            .build();

        final LocationCode locationCode = client.createLocation(request).getCode();

        // ACT
        final Optional<LocationDto> location = client.getLocation(locationCode);

        // ASSERT
        assertThat(location.isPresent(), is(true));
        assertThat(location.orElseThrow().getStatus(), is(expectedStatus));
    }
}
