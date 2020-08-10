package com.jonjam.pinboard.service.web.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.jonjam.pinboard.service.web.pages.location.mapper.LocationStatusWebMapper;
import com.jonjam.pinboard.service.web.pages.location.mapper.LocationStatusWebMapperImpl;
import com.jonjam.pinboard.service.web.pages.location.mapper.LocationWebMapper;
import com.jonjam.pinboard.service.web.pages.location.mapper.LocationWebMapperImpl;

public class WebServiceMapperModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(LocationStatusWebMapper.class).to(LocationStatusWebMapperImpl.class).in(Singleton.class);
    bind(LocationWebMapper.class).to(LocationWebMapperImpl.class).in(Singleton.class);
  }
}
