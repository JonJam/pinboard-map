//package com.jonjam.pinboard.service.location.svc.dao.location;
//
//import javax.inject.Inject;
//import java.util.Optional;
//
//import com.jonjam.pinboard.common.database.HandleWrapper;
//import com.jonjam.pinboard.common.test.DatabaseIntegrationTest;
//import com.jonjam.pinboard.service.location.svc.dao.location.model.InsertLocationRequest;
//import com.jonjam.pinboard.service.location.svc.dao.location.model.Location;
//import com.jonjam.pinboard.service.location.svc.dao.location.model.LocationStatus;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//
//@ExtendWith(DatabaseIntegrationTest.class)
//class LocationDaoIntegrationTest {
//    @Inject
//    private HandleWrapper handleWrapper;
//
//    private LocationDao locationDao;
//
//    @BeforeEach
//    void setup() {
//        locationDao = new LocationDao(handleWrapper, new LocationDao.LocationMapper());
//    }
//
//    @Test
//    void getByCode_noLocation_returnsEmpty() {
//        // ARRANGE
//        final long locationCode = 1004L;
//
//        // ACT
//        final Optional<Location> location = locationDao.getByCode(locationCode);
//
//        // ASSERT
//        assertThat(location.isEmpty(), is(true));
//    }
//
//    @Test
//    void getByCode_existingLocation_returnsExpectedLocation() {
//        // ARRANGE
//        final InsertLocationRequest request = new InsertLocationRequest.Builder()
//            .withLocationStatus(LocationStatus.ACTIVE)
//            .build();
//
//        final Location insertedLocation = locationDao.insertLocation(request);
//
//        // ACT
//        final Optional<Location> location = locationDao.getByCode(insertedLocation.getLocationCode());
//
//        // ASSERT
//        assertThat(location.isPresent(), is(true));
//        assertThat(location.orElseThrow().getLocationStatus(), is(request.getLocationStatus()));
//    }
//}
