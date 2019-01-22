package com.jonjam.pinboard.service.location;

// TODO Re-enable jackson
//import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class LocationJAX extends ResourceConfig {

  /**
   * Example resource config.
   */
  public LocationJAX() {
    super(
        // Resources
        MyResource.class
        //,

        // TODO Re-enable Guice
        // Register Guice
//        GuiceFeature.class,

        // TODO Re-enable jackson
        // Register Jackson
//        MyObjectMapperProvider.class,
//        JacksonFeature.class
    );
  }
}