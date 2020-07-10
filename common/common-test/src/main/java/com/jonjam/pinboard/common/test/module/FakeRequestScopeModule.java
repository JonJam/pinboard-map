package com.jonjam.pinboard.common.test.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.servlet.RequestScoped;

public class FakeRequestScopeModule extends AbstractModule {

    @Override
    protected void configure() {
        bindScope(RequestScoped.class, Scopes.NO_SCOPE);
    }
}