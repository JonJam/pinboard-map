package com.jonjam.pinboard.common.objectmodel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// TODO Enable this
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

// For creating custom immutable annotation. See https://immutables.github.io/style.html#custom-immutable-annotation
// Style customizations can be found here: https://github.com/immutables/immutables/blob/master/value-annotations/src/org/immutables/value/Value.java

// Default style - includes only builder
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
// TODO Enable this
//@JsonSerialize
@Value.Style(
    typeAbstract = "*", // Naming templates to detect base/raw type name from provided abstract value type name.
    typeImmutable = "Immutable*", // Name template to generate immutable implementation type by using base/raw type name
    typeBuilder = "Builder", // Generated builder class name.
    init = "with*", // Builder initialization method. i.e. "setter" in builder.
    defaultAsDefault = true,  // Default accessor methods defined in interfaces/traits to behave as if they annotated as Value.Default.
    allParameters = false, // If enabled mandatory attributes would be auto-propagated to be parameters of value object constructor.
    visibility = Value.Style.ImplementationVisibility.PACKAGE, // Specify the mode in which visibility of generated value type is derived from abstract value type.
    builderVisibility = Value.Style.BuilderVisibility.PACKAGE,  // Specify the mode in which visibility of generated value type is derived from abstract value type.
    overshadowImplementation = true, // Makes abstract value type predominantly used in generated signatures rather than immutable implementation class
    optionalAcceptNullable = true // Specify whether init, copy and factory methods and constructors for an unwrapped {@code X} of {@code Optional<X>} should accept {@code null} values as empty value.
)
public @interface Immutable {

  // Includes both builder and constructor
  @Target({ElementType.TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  // TODO Enable this ?
  //@JsonSerialize
  @Value.Style(
      typeAbstract = "*", // Naming templates to detect base/raw type name from provided abstract value type name.
      typeImmutable = "Immutable*", // Name template to generate immutable implementation type by using base/raw type name
      typeBuilder = "Builder", // Generated builder class name.
      init = "with*", // Builder initialization method. i.e. "setter" in builder.
      defaultAsDefault = true,  // Default accessor methods defined in interfaces/traits to behave as if they annotated as Value.Default.
      allParameters = true, // If enabled mandatory attributes would be auto-propagated to be parameters of value object constructor.
      visibility = Value.Style.ImplementationVisibility.PACKAGE, // Specify the mode in which visibility of generated value type is derived from abstract value type.
      builderVisibility = Value.Style.BuilderVisibility.PACKAGE,  // Specify the mode in which visibility of generated value type is derived from abstract value type.
      overshadowImplementation = true, // Makes abstract value type predominantly used in generated signatures rather than immutable implementation class
      optionalAcceptNullable = true // Specify whether init, copy and factory methods and constructors for an unwrapped {@code X} of {@code Optional<X>} should accept {@code null} values as empty value.
  )
  @interface WithAllArgsConstructor {
  }
}
