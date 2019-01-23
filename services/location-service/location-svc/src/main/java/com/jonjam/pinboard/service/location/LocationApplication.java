package com.jonjam.pinboard.service.location;

// TODO Re-enable jackson
//import org.glassfish.jersey.jackson.JacksonFeature;

import org.glassfish.jersey.server.ResourceConfig;

// TODO Sort out name.
public class LocationApplication extends ResourceConfig {

  /**
   * Example resource config.
   */
  public LocationApplication() {
    // TODO Re-enable Guice
    // Register Guice
    // GuiceFeature.class,

    // TODO Re-enable jackson
    // Register Jackson
    // MyObjectMapperProvider.class,
    // JacksonFeature.class
    super(
        // Resources
        MyResource.class
    );
  }
}