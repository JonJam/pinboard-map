package com.jonjam.pinboard.service.location.svc.dao.location.model;

import com.jonjam.pinboard.common.service.database.DatabaseBackedEnum;

public enum LocationStatus implements DatabaseBackedEnum {
    ACTIVE(1, "Active");

    private final int id;
    private final String description;

    LocationStatus(final int id, final String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public static LocationStatus fromDatabaseRepresentation(final int databaseRepresentation) {
        return DatabaseBackedEnum.fromDatabaseRepresentation(LocationStatus.class, databaseRepresentation);
    }
}

