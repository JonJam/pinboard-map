package com.jonjam.pinboardmap;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class MyApplication extends ResourceConfig {
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