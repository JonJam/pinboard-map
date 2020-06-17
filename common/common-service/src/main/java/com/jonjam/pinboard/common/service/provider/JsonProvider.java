package com.jonjam.pinboard.common.service.provider;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonjam.pinboard.common.objectmodel.ObjectMapperBuilder;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JsonProvider extends JacksonJsonProvider {
    public JsonProvider() {
        final ObjectMapper objectMapper = ObjectMapperBuilder.build();
        setMapper(objectMapper);
    }
}
