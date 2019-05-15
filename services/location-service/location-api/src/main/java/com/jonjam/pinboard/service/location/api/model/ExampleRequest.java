package com.jonjam.pinboard.service.location.api.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jonjam.pinboard.common.objectmodel.Immutable;

@Immutable
@JsonDeserialize(builder = ExampleRequest.Builder.class)
public abstract class ExampleRequest {
  public abstract String getBar();

  public static class Builder extends ImmutableExampleRequest.Builder {
  }
}