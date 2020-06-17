package com.jonjam.pinboard.service.location.svc.service;

import com.jonjam.pinboard.common.objectmodel.Immutable;

@Immutable
public abstract class TestDto {
  public abstract String getProp();

  public static class Builder extends ImmutableTestDto.Builder { }
}