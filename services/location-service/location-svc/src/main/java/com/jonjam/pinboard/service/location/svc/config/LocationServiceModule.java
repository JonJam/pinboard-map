package com.jonjam.pinboard.service.location.svc.config;

import com.google.inject.AbstractModule;
import com.jonjam.pinboard.service.location.IInjectedService;
import com.jonjam.pinboard.service.location.InjectedService;

public class LocationServiceModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(IInjectedService.class).to(InjectedService.class);
  }
}
