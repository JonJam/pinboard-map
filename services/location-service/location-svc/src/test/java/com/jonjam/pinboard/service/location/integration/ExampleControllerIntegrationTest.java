package com.jonjam.pinboard.service.location.integration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.jonjam.pinboard.service.location.api.ExampleService;
import com.jonjam.pinboard.service.location.api.model.ExampleResponse;
import org.junit.jupiter.api.Test;

class ExampleControllerIntegrationTest extends ServiceIntegrationTest {

  @Test
  void getIt_returnsExampleResponse() {
    final ExampleService service = getClient(ExampleService.class);
    final ExampleResponse response = service.getIt();

    assertThat(response.getHasValidProperty(), is(true));
  }
}
