package com.jonjam.pinboard.service.location.svc.module;

import com.google.inject.AbstractModule;
import com.jonjam.pinboard.service.location.svc.service.IInjectedService;
import com.jonjam.pinboard.service.location.svc.service.InjectedService;

public class LocationServiceModule extends AbstractModule {

  @Override
  protected void configure() {
    // TODO Remove
    bind(IInjectedService.class).to(InjectedService.class);
  }
}
