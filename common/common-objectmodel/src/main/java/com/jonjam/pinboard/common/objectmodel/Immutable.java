package com.jonjam.pinboard.common.objectmodel;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.immutables.value.Value;

// For creating custom immutable annotation. See https://immutables.github.io/style.html#custom-immutable-annotation
// Style customizations can be found here: https://github.com/immutables/immutables/blob/master/value-annotations/src/org/immutables/value/Value.java
// Enable Jackson automatic serialization. See https://immutables.github.io/json.html#reducing-annotation-clutter

// Default style - includes only builder
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@JsonSerialize
@Value.Style(
    // Naming templates to detect base/raw type name from provided abstract value type name.
    typeAbstract = "*",
    // Name template to generate immutable implementation type by using base/raw type name
    typeImmutable = "Immutable*",
    // Generated builder class name.
    typeBuilder = "Builder",
    // Builder initialization method. i.e. "setter" in builder.
    init = "with*",
    /* Default accessor methods defined in interfaces/traits
     * to behave as if they annotated as Value.Default.
     */
    defaultAsDefault = true,
    /* If enabled mandatory attributes would be auto-propagated
     * to be parameters of value object constructor.
     */
    allParameters = false,
    /* Specify the mode in which visibility of generated value
     * type is derived from abstract value type.
     */
    visibility = Value.Style.ImplementationVisibility.PACKAGE,
    /* Specify the mode in which visibility of generated value
     * type is derived from abstract value type.
     */
    builderVisibility = Value.Style.BuilderVisibility.PACKAGE,
    /* Makes abstract value type predominantly used in generated
     * signatures rather than immutable implementation class
     */
    overshadowImplementation = true,
    /* Specify whether init, copy and factory methods and constructors
     * for an unwrapped {@code X} of {@code Optional<X>} should
     * accept {@code null} values as empty value.
     */
    optionalAcceptNullable = true,
    // Enable strict builders
    strictBuilder = true
)
public @interface Immutable {
  // Includes both builder and constructor
  @Target({ElementType.TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @JsonSerialize
  @Value.Style(
      // Naming templates to detect base/raw type name from provided abstract value type name.
      typeAbstract = "*",
      // Name template to generate immutable implementation type by using base/raw type name
      typeImmutable = "Immutable*",
      // Generated builder class name.
      typeBuilder = "Builder",
      // Builder initialization method. i.e. "setter" in builder.
      init = "with*",
      /* Default accessor methods defined in interfaces/traits
       * to behave as if they annotated as Value.Default.
       */
      defaultAsDefault = true,
      /* If enabled mandatory attributes would be auto-propagated
       * to be parameters of value object constructor.
       */
      allParameters = false,
      /* Specify the mode in which visibility of generated value
       * type is derived from abstract value type.
       */
      visibility = Value.Style.ImplementationVisibility.PACKAGE,
      /* Specify the mode in which visibility of generated value
       * type is derived from abstract value type.
       */
      builderVisibility = Value.Style.BuilderVisibility.PACKAGE,
      /* Makes abstract value type predominantly used in generated
       * signatures rather than immutable implementation class
       */
      overshadowImplementation = true,
      /* Specify whether init, copy and factory methods and constructors
       * for an unwrapped {@code X} of {@code Optional<X>} should
       * accept {@code null} values as empty value.
       */
      optionalAcceptNullable = true,
      // Enable strict builders
      strictBuilder = true
  )
  @interface WithAllArgsConstructor {
  }
}
