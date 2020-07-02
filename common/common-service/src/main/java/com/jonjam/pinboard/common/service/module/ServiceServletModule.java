package com.jonjam.pinboard.common.service.module;

import com.google.inject.servlet.ServletModule;

// Required to support RequestScope in Guice (see https://github.com/google/guice/wiki/ServletModule)
public class ServiceServletModule extends ServletModule {

    @Override
    protected void configureServlets() {
    }
}
