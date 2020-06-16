package com.jonjam.pinboard.service.location.svc.config.common;

import org.glassfish.jersey.server.ResourceConfig;

import java.util.List;

import com.google.inject.Module;
import com.google.inject.util.Modules;
import org.glassfish.hk2.api.PostConstruct;

public abstract class ServiceResourceConfig<T extends ServiceConfiguration> extends ResourceConfig implements PostConstruct {
  private final ConfigurationFactory<T> configurationFactory;
  private T config;

  protected ServiceResourceConfig(final ConfigurationFactory<T> configurationFactory) {
    this.configurationFactory = configurationFactory;

    loadConfiguration();
  }

  public void postConstruct() {
    final GuiceFeature guiceFeature = new GuiceFeature(configureGuiceModules(config));

    register(guiceFeature);

    register(new JsonProvider());

    packages(getServiceControllerPackageName());
  }

  protected abstract Module getConfigurationModule(final T config);

  protected abstract Module getServiceModule();

  protected abstract String getServiceControllerPackageName();

  private void loadConfiguration() {
    final ConfigurationLoader configLoader = new ConfigurationLoader();

//    String environment = System.getProperty(VIATOR_ENVIRONMENT_PROPERTY);
//
//    if (environment == null || environment.length() == 0) {
//      environment = "dev";
//    }

    config = configLoader.readConfig(
//        environment,
//        System.getProperty(HOSTNAME_PROPERTY),
//        System.getProperty(VIATOR_POP_PROPERTY),
//        System.getProperty(KUBERNETES_CLUSTER),
//        System.getProperty(KUBERNETES_CLUSTER_LETTER),
        configurationFactory
    );
  }

  private Module configureGuiceModules(final T config) {
    final List<Module> modules = List.of(
        getConfigurationModule(config),
        getServiceModule());

    return Modules.combine(modules);
  }
}
