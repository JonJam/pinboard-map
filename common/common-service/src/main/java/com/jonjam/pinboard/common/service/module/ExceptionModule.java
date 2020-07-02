package com.jonjam.pinboard.common.service.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.jonjam.pinboard.common.service.exception.InternalRemoteExceptionMapper;

public class ExceptionModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(InternalRemoteExceptionMapper.class).in(Singleton.class);
    }
}
