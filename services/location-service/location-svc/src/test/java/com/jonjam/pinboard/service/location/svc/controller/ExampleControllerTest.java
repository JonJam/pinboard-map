package com.jonjam.pinboard.service.location.svc.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import com.jonjam.pinboard.service.location.api.model.ExampleResponse;
import com.jonjam.pinboard.service.location.svc.service.IInjectedService;
import com.jonjam.pinboard.service.location.svc.service.TestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExampleControllerTest {
  private static final String PROP_VALUE = "hi";

  private ExampleController exampleController;

  @BeforeEach
  void beforeEach(final @Mock IInjectedService injectedService) {
    final TestDto value = new TestDto.Builder()
        .withProp(PROP_VALUE)
        .build();
    when(injectedService.test()).thenReturn(value);

    exampleController = new ExampleController(injectedService);
  }

  @Test
  void getIt_returnsExampleResponse() {
    final ExampleResponse response = exampleController.getIt();

    assertThat(response.getBar(), is(PROP_VALUE));
    assertThat(response.canValidProperty(), is(true));
    assertThat(response.hasValidProp(), is(true));
    assertThat(response.getHasValidProperty(), is(true));
    assertThat(response.isValidProperty(), is(true));
    assertThat(response.shouldValidProperty(), is(true));
  }
}