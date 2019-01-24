package com.jonjam.pinboard.service.location.svc.config;

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

public class GuiceFeature implements Feature {

  private final Module[] modules;

  public GuiceFeature(Module... modules) {
    this.modules = modules;
  }

  @Override
  public boolean configure(FeatureContext context) {
    InjectionManager injectionManager = InjectionManagerProvider.getInjectionManager(context);

    ServiceLocator serviceLocator = injectionManager.getInstance(ServiceLocator.class);

    GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
    GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);

    // TODO Set stage
    Injector injector = Guice.createInjector(modules);

    guiceBridge.bridgeGuiceInjector(injector);

    return true;
  }
}