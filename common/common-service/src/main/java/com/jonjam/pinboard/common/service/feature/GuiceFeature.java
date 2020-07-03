package com.jonjam.pinboard.common.service.feature;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import javax.annotation.Priority;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import com.google.inject.Stage;
import com.jonjam.pinboard.common.service.config.GuiceConfiguration;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.InjectionManagerProvider;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

@Priority(1)
public class GuiceFeature implements Feature {
  private final GuiceConfiguration guiceConfiguration;
  private final Module[] modules;

  public GuiceFeature(
      final GuiceConfiguration guiceConfiguration,
      final Module... modules) {
    this.guiceConfiguration = guiceConfiguration;
    this.modules = modules;
  }

  @Override
  public boolean configure(final FeatureContext context) {
    final InjectionManager injectionManager = InjectionManagerProvider.getInjectionManager(context);

    final ServiceLocator serviceLocator = injectionManager.getInstance(ServiceLocator.class);

    GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);

    final GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);

    final Stage guiceMode = getStage();

    final Injector injector = Guice.createInjector(guiceMode, modules);

    guiceBridge.bridgeGuiceInjector(injector);

    return true;
  }

  private Stage getStage() {
    return this.guiceConfiguration.useDevelopmentStage() ? Stage.DEVELOPMENT : Stage.PRODUCTION;
  }
}