package com.jonjam.pinboard.service.location.svc.dao.location;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.jonjam.pinboard.common.service.database.HandleWrapper;
import com.jonjam.pinboard.service.location.svc.dao.location.model.InsertLocationRequest;
import com.jonjam.pinboard.service.location.svc.dao.location.model.Location;
import com.jonjam.pinboard.service.location.svc.dao.location.model.LocationStatus;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class LocationDao {

    private final HandleWrapper handleWrapper;

    private final LocationMapper locationMapper;

    @Inject
    public LocationDao(
        final HandleWrapper handleWrapper,
        final LocationMapper locationMapper) {
        this.handleWrapper = handleWrapper;
        this.locationMapper = locationMapper;
    }

    public Optional<Location> getByCode(final long locationCode) {
        return handleWrapper.createQuery("SELECT location_id, location_code, location_status_id FROM location WHERE location_code = :locationCode")
                            .bind("locationCode", locationCode)
                            .map(locationMapper)
                            .findOne();
    }

    public Location insertLocation(final InsertLocationRequest request) {
        return handleWrapper.createUpdate("INSERT INTO location (location_status_id) VALUES (:locationStatusId)")
                            .bind("locationStatusId", request.getLocationStatus().getId())
                            .executeAndReturnGeneratedKeys()
                            .map(locationMapper)
                            .one();
    }

    public static class LocationMapper implements RowMapper<Location> {
        @Override
        public Location map(final ResultSet rs, final StatementContext ctx) throws SQLException {
            final LocationStatus status = LocationStatus.fromDatabaseRepresentation(rs.getInt("location_status_id"));

            return new Location.Builder()
                .withLocationId(rs.getLong("location_id"))
                .withLocationCode(rs.getLong("location_code"))
                .withLocationStatus(status)
                .build();
        }
    }
}
