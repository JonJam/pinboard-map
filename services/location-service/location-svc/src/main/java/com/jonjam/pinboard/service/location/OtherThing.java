package com.jonjam.pinboard.service.location;

import java.util.List;
import java.util.Set;
import org.immutables.value.Value;

// TODO Remove.
@Value.Immutable
public abstract class OtherThing {
  public abstract int foo();

  public abstract String bar();

  public abstract List<Integer> buz();

  public abstract Set<Long> crux();
}
