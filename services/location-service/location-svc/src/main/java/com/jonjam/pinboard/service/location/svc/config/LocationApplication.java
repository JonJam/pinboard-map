package com.jonjam.pinboard.service.location.svc.config;

import com.jonjam.pinboard.service.location.ExampleController;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

// TODO Sort out name.
public class LocationApplication extends ResourceConfig {

  /**
   * Example resource config.
   */
  public LocationApplication() {
    registerGuice();
    registerJackson();
    registerControllers();
  }

  private void registerJackson() {
    register(MyObjectMapperProvider.class);
    register(JacksonFeature.class);
  }

  private void registerGuice() {
    register(new GuiceFeature(new LocationModule()));
  }

  private void registerControllers() {
    register(ExampleController.class);
  }
}