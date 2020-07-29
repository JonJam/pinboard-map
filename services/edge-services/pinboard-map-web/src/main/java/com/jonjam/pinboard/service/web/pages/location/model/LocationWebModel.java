package com.jonjam.pinboard.service.web.pages.location.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jonjam.pinboard.common.objectmodel.Immutable;
import com.jonjam.pinboard.service.location.api.ref.LocationCode;

// TODO Remove
@Immutable
@JsonDeserialize(builder = LocationWebModel.Builder.class)
public abstract class LocationWebModel {
    public abstract LocationCode getCode();

    // Required for MapStruct
    public static LocationWebModel.Builder builder() {
        return new LocationWebModel.Builder();
    }

    public static class Builder extends ImmutableLocationWebModel.Builder {
    }
}

