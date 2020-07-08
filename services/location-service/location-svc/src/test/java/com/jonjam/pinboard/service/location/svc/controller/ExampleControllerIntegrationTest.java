package com.jonjam.pinboard.service.location.svc.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.jonjam.pinboard.common.feign.RestClientUtil;
import com.jonjam.pinboard.common.test.ServiceWithDatabaseIntegrationTest;
import com.jonjam.pinboard.common.test.container.ServiceTestContainer;
import com.jonjam.pinboard.service.location.api.ExampleService;
import com.jonjam.pinboard.service.location.api.model.ExampleResponse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ServiceWithDatabaseIntegrationTest.class)
class ExampleControllerIntegrationTest {
    private static ExampleService client;

    @BeforeAll
    static void beforeAll() {
      client = RestClientUtil.createJAXRSClient(ExampleService.class, ServiceTestContainer.getUrl());
    }

    @Test
    void getIt_returnsExampleResponse() {
      // ACT
      final ExampleResponse response = client.getIt();

      // ASSERT
      assertThat(response.getHasValidProperty(), is(true));
    }
}
