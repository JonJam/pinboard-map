package com.jonjam.pinboard.service.location.api.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jonjam.pinboard.common.objectmodel.Immutable;

@Immutable
@JsonDeserialize(builder = ExampleResponse.Builder.class)
public abstract class ExampleResponse {
  public abstract String getBar();

  public abstract boolean getHasValidProperty();

  public abstract boolean isValidProperty();

  public abstract boolean shouldValidProperty();

  public abstract boolean hasValidProp();

  public abstract boolean canValidProperty();

  public static class Builder extends ImmutableExampleResponse.Builder {
  }
}