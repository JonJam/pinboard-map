package com.jonjam.pinboard.service.location.svc.config;

import com.google.inject.Module;
import com.jonjam.pinboard.service.location.svc.config.common.ServiceResourceConfig;

public class LocationServiceJAX extends ServiceResourceConfig<LocationServiceConfiguration> {
  private static final String CONTROLLERS_PACKAGES = "com.jonjam.pinboard.service.location.svc.controller";

  public LocationServiceJAX() {
      super(new LocationConfigurationFactory());
  }

  @Override
  protected Module getConfigurationModule(final LocationServiceConfiguration config) {
    return new LocationServiceConfigurationModule(config);
  }

  @Override
  protected Module getServiceModule() {
    return new LocationServiceModule();
  }

  @Override
  protected String getServiceControllerPackageName() {
      return CONTROLLERS_PACKAGES;
  }
}