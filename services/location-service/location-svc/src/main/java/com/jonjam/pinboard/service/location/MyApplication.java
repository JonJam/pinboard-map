package com.jonjam.pinboard.service.location;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class MyApplication extends ResourceConfig {

  /**
   * Example resource config.
   */
  public MyApplication() {
    super(
        // Resources
        MyResource.class,

        // Register Guice
        GuiceFeature.class,

        // Register Jackson
        MyObjectMapperProvider.class,
        JacksonFeature.class
    );
  }
}