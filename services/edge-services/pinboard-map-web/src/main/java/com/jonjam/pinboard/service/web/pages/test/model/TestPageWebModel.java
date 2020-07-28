package com.jonjam.pinboard.service.web.pages.test.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jonjam.pinboard.common.objectmodel.Immutable;

// TODO Remove
@Immutable
@JsonDeserialize(builder = TestPageWebModel.Builder.class)
public abstract class TestPageWebModel {

    public abstract String getValue();

    // Required for MapStruct
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends ImmutableTestPageWebModel.Builder { }
}

