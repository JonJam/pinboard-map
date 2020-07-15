package com.jonjam.pinboard.common.mapstruct.spi;

import javax.lang.model.element.ExecutableElement;

import org.mapstruct.ap.spi.DefaultAccessorNamingStrategy;
import org.mapstruct.ap.spi.util.IntrospectorUtils;

/**
 * Based on ImmutablesAccessorNamingStrategy with the following changes:
 * - From methods are not generated as we are using strict builders.
 * - We use with instead of set for setter methods.
 */
public class CustomImmutablesAccessorNamingStrategy extends DefaultAccessorNamingStrategy {

    private static final int SET_PREFIX_LENGTH = 4;

    @Override
    public String getPropertyName(final ExecutableElement getterOrSetterMethod) {
        final String methodName = getterOrSetterMethod.getSimpleName()
                                                      .toString();
        if (isFluentSetter(getterOrSetterMethod)) {
            if (methodName.startsWith("with") &&
                methodName.length() > SET_PREFIX_LENGTH &&
                Character.isUpperCase(methodName.charAt(SET_PREFIX_LENGTH))) {
                return IntrospectorUtils.decapitalize(methodName.substring(SET_PREFIX_LENGTH));
            } else {
                return methodName;
            }
        }

        return IntrospectorUtils.decapitalize( methodName.substring(methodName.startsWith("is") ? 2 : 3 ));
    }
}
