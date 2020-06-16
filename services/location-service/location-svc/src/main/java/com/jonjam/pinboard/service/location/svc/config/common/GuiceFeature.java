package com.jonjam.pinboard.service.location.svc.config.common;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.InjectionManagerProvider;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

// TODO Move to common project
public class GuiceFeature implements Feature {

  private final Module[] modules;

  public GuiceFeature(final Module... modules) {
    this.modules = modules;
  }

  @Override
  public boolean configure(final FeatureContext context) {
    final InjectionManager injectionManager = InjectionManagerProvider.getInjectionManager(context);

    final ServiceLocator serviceLocator = injectionManager.getInstance(ServiceLocator.class);

    GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);

    final GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);

    // TODO Set stage
    final Injector injector = Guice.createInjector(modules);

    guiceBridge.bridgeGuiceInjector(injector);

    return true;
  }
}