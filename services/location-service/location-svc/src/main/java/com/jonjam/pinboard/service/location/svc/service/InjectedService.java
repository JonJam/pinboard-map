package com.jonjam.pinboard.service.location.svc.service;

public class InjectedService implements IInjectedService {

  /**
   * Gets a test immutable.
   * @return A test immutable.
   */
  public TestDto test() {
    return new TestDto.Builder()
        .withProp("hi")
        .build();
  }
}