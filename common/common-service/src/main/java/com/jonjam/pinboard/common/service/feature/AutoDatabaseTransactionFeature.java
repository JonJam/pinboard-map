package com.jonjam.pinboard.common.service.feature;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

import com.jonjam.pinboard.common.database.AutoTransactionHandleContext;
import org.glassfish.jersey.server.ContainerResponse;

public class AutoDatabaseTransactionFeature implements DynamicFeature {

    private Provider<AutoTransactionHandleContext> autoTransactionHandleContextProvider;

    @Inject
    public AutoDatabaseTransactionFeature(final Provider<AutoTransactionHandleContext> autoTransactionHandleContextProvider) {
        this.autoTransactionHandleContextProvider = autoTransactionHandleContextProvider;
    }

    @Override
    public void configure(
        final ResourceInfo resourceInfo,
        final FeatureContext context) {
        context.register(new AutoTransactionFilter(autoTransactionHandleContextProvider));
    }

    @Priority(1)
    private static class AutoTransactionFilter implements ContainerRequestFilter, ContainerResponseFilter {

        private final Provider<AutoTransactionHandleContext> autoTransactionHandleContextProvider;

        AutoTransactionFilter(final Provider<AutoTransactionHandleContext> autoTransactionHandleContextProvider) {
            this.autoTransactionHandleContextProvider = autoTransactionHandleContextProvider;
        }

        @Override
        public void filter(final ContainerRequestContext requestContext) {
            // trigger creation of transaction context, and enable auto-management
            // BUT DO NOT IMMEDIATELY START A TRANSACTION FOR EVERY REQUEST!
            autoTransactionHandleContextProvider.get();
        }

        @Override
        public void filter(
            final ContainerRequestContext requestContext,
            final ContainerResponseContext responseContext) {
            // NOTE: wrap in try-with-resources in order to perform any pending commit/rollback and release of the connection in the "finally" phase...
            try (final AutoTransactionHandleContext context = autoTransactionHandleContextProvider.get()) {
                if (responseContext instanceof ContainerResponse) {
                    final ContainerResponse containerResponse = (ContainerResponse) responseContext;

                    if (containerResponse.isMappedFromException()) {
                        context.markRollback();
                    }
                }
            }
        }
    }
}

