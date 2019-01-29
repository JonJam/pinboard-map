package com.jonjam.pinboard.service.location;

import java.util.List;
import java.util.Set;
import com.jonjam.pinboard.common.objectmodel.Immutable;

// TODO Remove.
@Immutable
public abstract class FoobarValue {
  public abstract int foo();

  public abstract String bar();

  public abstract List<Integer> buz();

  public abstract Set<Long> crux();
}