package com.jonjam.pinboard.service.web;

import com.google.inject.Module;
import com.jonjam.pinboard.common.service.ServiceResourceConfig;
import com.jonjam.pinboard.service.web.config.WebServiceConfiguration;
import com.jonjam.pinboard.service.web.config.WebServiceConfigurationFactory;
import com.jonjam.pinboard.service.web.module.WebServiceConfigurationModule;
import com.jonjam.pinboard.service.web.module.WebServiceMapperModule;
import com.jonjam.pinboard.service.web.module.WebServiceModule;

public class WebServiceJAX extends ServiceResourceConfig<WebServiceConfiguration> {
  private static final String CONTROLLERS_PACKAGES = "com.jonjam.pinboard.service.web.pages";

  public WebServiceJAX() {
      super(new WebServiceConfigurationFactory());
  }

  @Override
  protected Module getConfigurationModule(final WebServiceConfiguration config) {
    return new WebServiceConfigurationModule(config);
  }

  @Override
  protected Module getMapperModule() {
    return new WebServiceMapperModule();
  }

  @Override
  protected Module getServiceModule() {
    return new WebServiceModule();
  }

  @Override
  protected String getServiceControllerPackageName() {
      return CONTROLLERS_PACKAGES;
  }
}