package com.jonjam.pinboard.service.location;

import com.jonjam.pinboard.common.objectmodel.Immutable;

@Immutable
public abstract class ExampleResponse {
  public abstract String getBar();

  public abstract boolean getHasValidProperty();

  public abstract boolean isValidProperty();

  public abstract boolean shouldValidProperty();

  public abstract boolean hasValidProp();

  public abstract boolean canValidProperty();

  public static class Builder extends ImmutableExampleResponse.Builder {}
}