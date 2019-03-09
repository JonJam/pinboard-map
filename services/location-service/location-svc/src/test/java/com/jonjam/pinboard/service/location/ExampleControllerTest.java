package com.jonjam.pinboard.service.location;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExampleControllerTest {
  private ExampleController exampleController;

  @BeforeEach
  void beforeEach() {
    final InjectedService injectedService = new InjectedService();

    exampleController = new ExampleController(injectedService);
  }

  @Test
  void getIt_returnsExampleResponse() {
    final ExampleResponse response = exampleController.getIt();

    assertThat(response.getBar(), is("hi"));
    assertThat(response.canValidProperty(), is(true));
    assertThat(response.hasValidProp(), is(true));
    assertThat(response.getHasValidProperty(), is(true));
    assertThat(response.isValidProperty(), is(true));
    assertThat(response.shouldValidProperty(), is(true));
  }
}
