package com.jonjam.pinboard.common.service.module;

import javax.inject.Provider;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.jonjam.pinboard.common.feign.FeignBuilder;
import com.jonjam.pinboard.common.feign.RemoteExceptionDecoder;
import com.jonjam.pinboard.common.service.config.ServiceClientConfiguration;
import feign.codec.ErrorDecoder;

public abstract class ServiceClientModule<TConfig extends ServiceClientConfiguration> extends AbstractModule {
    private final TConfig serviceClientConfiguration;

    public ServiceClientModule(final TConfig serviceClientConfiguration) {
        this.serviceClientConfiguration = serviceClientConfiguration;
    }

    @Override
    protected void configure() {
        bind(ErrorDecoder.class).to(RemoteExceptionDecoder.class).in(Singleton.class);

        final Map<Class, String> serviceClassToUrlMap = getServiceClassToUrlMap(serviceClientConfiguration);

        serviceClassToUrlMap.forEach(this::bindService);
    }

    protected abstract Map<Class, String> getServiceClassToUrlMap(final TConfig config);

    private void bindService(
        final Class serviceClass,
        final String url) {
        final Provider<ObjectMapper> objectMapperProvider = getProvider(ObjectMapper.class);
        final Provider<ErrorDecoder> errorDecoderProvider = getProvider(ErrorDecoder.class);

        bind(serviceClass).toProvider(constructServiceProvider(objectMapperProvider, errorDecoderProvider, serviceClass, url));
    }

    private <TServiceClass> Provider<TServiceClass> constructServiceProvider(
        final Provider<ObjectMapper> objectMapperProvider,
        final Provider<ErrorDecoder> errorDecoderProvider,
        final Class<TServiceClass> serviceClass,
        final String url) {

        return () -> {
            final ObjectMapper objectMapper = objectMapperProvider.get();
            final ErrorDecoder errorDecoder = errorDecoderProvider.get();

            return FeignBuilder.buildWithErrorDecoder(objectMapper, errorDecoder, serviceClass, url);
        };
    }
}
