package com.jonjam.pinboard.service.location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    assertEquals("hi", response.getBar());
    assertTrue(response.canValidProperty());
    assertTrue(response.hasValidProp());
    assertTrue(response.getHasValidProperty());
    assertTrue(response.isValidProperty());
    assertTrue(response.shouldValidProperty());
  }
}
