package com.jonjam.pinboard.service.location.svc.config.common;

import org.glassfish.jersey.server.ResourceConfig;

import java.util.List;

import com.google.inject.Module;
import com.google.inject.util.Modules;
import org.glassfish.hk2.api.PostConstruct;

public abstract class ServiceResourceConfig<T extends ServiceConfiguration> extends ResourceConfig implements PostConstruct {
  private T config;

  protected ServiceResourceConfig(final ConfigurationFactory<T> configurationFactory) {
      config = new ConfigurationLoader<>(configurationFactory).readConfig();
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

  private Module configureGuiceModules(final T config) {
    final List<Module> modules = List.of(
        getConfigurationModule(config),
        getServiceModule());

    return Modules.combine(modules);
  }
}
