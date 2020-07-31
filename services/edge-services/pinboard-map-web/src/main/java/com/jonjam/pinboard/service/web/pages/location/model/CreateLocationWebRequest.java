package com.jonjam.pinboard.service.web.pages.location.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jonjam.pinboard.common.objectmodel.Immutable;

// TODO Remove
@Immutable
@JsonDeserialize(builder = CreateLocationWebRequest.Builder.class)
public abstract class CreateLocationWebRequest {

    @NotEmpty
    public abstract String getName();

    @Min(1)
    public abstract int getSize();

    // Required for MapStruct
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends ImmutableCreateLocationWebRequest.Builder { }
}

