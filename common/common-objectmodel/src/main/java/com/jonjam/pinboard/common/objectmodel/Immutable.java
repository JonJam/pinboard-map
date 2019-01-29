package com.jonjam.pinboard.common.objectmodel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

// For creating custom immutable annotation. See https://immutables.github.io/style.html#custom-immutable-annotation
// Default style - includes only builder
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
//@JsonSerialize
@Value.Style(
    typeAbstract = "*",
    typeImmutable = "Immutable*", // prefix immutable concrete implementations with Immutable
    typeBuilder = "Builder", // name builder classes as Builder (nested within immutable concrete class)
    init = "with*", // Builder initialization methods will have 'with' prefix
    defaultAsDefault = true, // java 8 default methods will be automatically turned into @Value.Default
    allParameters = false,
    visibility = Value.Style.ImplementationVisibility.PACKAGE, // hide the immutable implementation class
    builderVisibility = Value.Style.BuilderVisibility.PACKAGE,  // hide the builder (and expose via nested class in your DTO)
    overshadowImplementation = true,
    optionalAcceptNullable = true
)
public @interface Immutable {

  // Includes both builder and constructor
  @Target({ElementType.TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  //@JsonSerialize
  @Value.Style(
      typeAbstract = "*",
      typeImmutable = "Immutable*", // prefix immutable concrete implementations with Immutable
      typeBuilder = "Builder", // name builder classes as Builder (nested within immutable concrete class)
      init = "with*", // Builder initialization methods will have 'with' prefix
      defaultAsDefault = true, // java 8 default methods will be automatically turned into @Value.Default
      allParameters = true, // include all properties as arguments on the constructor - opt out via @Value.Parameter(value = false)
      visibility = Value.Style.ImplementationVisibility.PACKAGE, // hide the immutable implementation class
      builderVisibility = Value.Style.BuilderVisibility.PACKAGE,  // hide the builder (and expose via nested class in your DTO)
      overshadowImplementation = true,
      optionalAcceptNullable = true
  )
  @interface WithAllArgsConstructor {
  }
}
