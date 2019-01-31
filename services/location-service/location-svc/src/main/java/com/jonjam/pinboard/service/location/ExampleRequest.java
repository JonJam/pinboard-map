package com.jonjam.pinboard.service.location;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jonjam.pinboard.common.objectmodel.Immutable;

@Immutable
// TODO Work out so do not need to specify this
@JsonDeserialize(builder = ExampleRequest.Builder.class)
public abstract class ExampleRequest {
  public abstract String getBar();

  public static class Builder extends ImmutableExampleRequest.Builder {}
}