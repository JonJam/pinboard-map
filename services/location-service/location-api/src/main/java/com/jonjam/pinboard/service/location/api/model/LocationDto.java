package com.jonjam.pinboard.service.location.api.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jonjam.pinboard.common.objectmodel.Immutable;
import com.jonjam.pinboard.service.location.api.ref.LocationCode;

@Immutable
@JsonDeserialize(builder = LocationDto.Builder.class)
public abstract class LocationDto {
  public abstract LocationCode getCode();

  public abstract LocationStatusDto getStatus();

  // Required for MapStruct
  public static Builder builder() {
    return new Builder();
  }

  public static class Builder extends ImmutableLocationDto.Builder {
  }
}