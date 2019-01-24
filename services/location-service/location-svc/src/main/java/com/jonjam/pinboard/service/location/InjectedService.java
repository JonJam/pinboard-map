package com.jonjam.pinboard.service.location;

public class InjectedService implements IInjectedService {
  public Test test() {
    return new Test("hi");
  }
}
