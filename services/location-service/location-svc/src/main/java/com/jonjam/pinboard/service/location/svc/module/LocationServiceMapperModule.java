package com.jonjam.pinboard.service.location.svc.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.jonjam.pinboard.service.location.svc.mapper.LocationMapper;
import com.jonjam.pinboard.service.location.svc.mapper.LocationMapperImpl;
import com.jonjam.pinboard.service.location.svc.mapper.LocationStatusMapper;
import com.jonjam.pinboard.service.location.svc.mapper.LocationStatusMapperImpl;

public class LocationServiceMapperModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(LocationStatusMapper.class).to(LocationStatusMapperImpl.class).in(Singleton.class);
    bind(LocationMapper.class).to(LocationMapperImpl.class).in(Singleton.class);
  }
}
