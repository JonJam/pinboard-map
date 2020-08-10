package com.jonjam.pinboard.common.service;

import com.google.common.collect.Lists;
import com.jonjam.pinboard.common.configuration.ConfigurationFactory;
import com.jonjam.pinboard.common.configuration.ConfigurationLoader;
import com.jonjam.pinboard.common.service.config.ServiceConfiguration;
import com.jonjam.pinboard.common.exception.AbstractRemoteExceptionMapper;
import com.jonjam.pinboard.common.exception.InternalRemoteExceptionMapper;
import com.jonjam.pinboard.common.service.feature.GuiceFeature;
import com.jonjam.pinboard.common.service.feature.AutoDatabaseTransactionFeature;
import com.jonjam.pinboard.common.service.module.DatabaseModule;
import com.jonjam.pinboard.common.service.module.ExceptionModule;
import com.jonjam.pinboard.common.service.module.JsonModule;
import com.jonjam.pinboard.common.service.module.NoOpModule;
import com.jonjam.pinboard.common.service.module.ServiceServletModule;
import com.jonjam.pinboard.common.service.provider.JsonProvider;
import com.jonjam.pinboard.common.service.provider.ReferenceParamConverterProvider;
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
    registerFeatures();

    registerProviders();

    packages(getServiceControllerPackageName());

    register(getExceptionMapper());

    addToPostConstruct();
  }

  private void registerProviders() {
    register(new JsonProvider());

    // Param converter providers
    register(ReferenceParamConverterProvider.class);
  }

  private Class<? extends AbstractRemoteExceptionMapper> getExceptionMapper() {
    return InternalRemoteExceptionMapper.class;
  }

  protected abstract Module getConfigurationModule(final T config);

  protected abstract Module getMapperModule();

  protected abstract Module getServiceModule();

  protected Module getServiceClientModule(final T config) {
    return NoOpModule.get();
  }

  protected boolean useDb() {
    return false;
  }

  protected boolean useServiceClient() {
    return false;
  }

  protected abstract String getServiceControllerPackageName();

  protected void addToPostConstruct() {
    // NO-OP
  }

  private void registerFeatures() {
    register(createGuiceFeature());

    if (useDb()) {
      register(AutoDatabaseTransactionFeature.class);
    }
  }

  private GuiceFeature createGuiceFeature() {
    final List<Module> modules = Lists.newArrayList(
        new ExceptionModule(),
        getConfigurationModule(config),
        getMapperModule(),
        new JsonModule(),
        getServiceModule(),
        new ServiceServletModule());

    if (useDb()) {
      modules.add(new DatabaseModule());
    }

    if (useServiceClient()) {
      modules.add(getServiceClientModule(config));
    }

    return new GuiceFeature(
        this.config.getGuiceConfiguration(),
        Modules.combine(modules));
  }
}
