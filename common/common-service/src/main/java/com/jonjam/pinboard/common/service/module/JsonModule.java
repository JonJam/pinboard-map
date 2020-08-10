package com.jonjam.pinboard.common.service.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.jonjam.pinboard.common.objectmodel.ObjectMapperBuilder;

public class JsonModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ObjectMapper.class).toInstance(ObjectMapperBuilder.build());
    }
}
