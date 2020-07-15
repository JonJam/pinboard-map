package com.jonjam.pinboard.service.location.api.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jonjam.pinboard.common.objectmodel.Immutable;

@Immutable
@JsonDeserialize(builder = LocationDto.Builder.class)
public abstract class LocationDto {
  public abstract long getCode();

  public abstract LocationStatusDto getStatus();

  // Required for MapStruct
  public static Builder builder() {
    return new Builder();
  }

  public static class Builder extends ImmutableLocationDto.Builder {
  }
}