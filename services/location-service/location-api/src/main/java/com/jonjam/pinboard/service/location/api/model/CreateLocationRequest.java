package com.jonjam.pinboard.service.location.api.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jonjam.pinboard.common.objectmodel.Immutable;

@Immutable
@JsonDeserialize(builder = CreateLocationRequest.Builder.class)
public abstract class CreateLocationRequest {

    public abstract LocationStatusDto getStatus();

    // Required for MapStruct
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends ImmutableCreateLocationRequest.Builder { }
}

