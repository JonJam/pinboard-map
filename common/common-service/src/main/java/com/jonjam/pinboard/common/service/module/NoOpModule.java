package com.jonjam.pinboard.common.service.module;

import com.google.inject.AbstractModule;

public class NoOpModule {

    private static final AbstractModule NO_OP_MODULE_INSTANCE = new NoOpModuleInstance();

    public static AbstractModule get() {
        return NO_OP_MODULE_INSTANCE;
    }

    private static class NoOpModuleInstance extends AbstractModule {
        @Override
        protected void configure() {
            // NO-OP
        }
    }
}
