package com.jonjam.pinboard.service.location.svc;

import com.google.inject.Module;
import com.jonjam.pinboard.common.service.ServiceResourceConfig;
import com.jonjam.pinboard.service.location.svc.config.LocationConfigurationFactory;
import com.jonjam.pinboard.service.location.svc.config.LocationServiceConfiguration;
import com.jonjam.pinboard.service.location.svc.module.LocationServiceConfigurationModule;
import com.jonjam.pinboard.service.location.svc.module.LocationServiceModule;

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