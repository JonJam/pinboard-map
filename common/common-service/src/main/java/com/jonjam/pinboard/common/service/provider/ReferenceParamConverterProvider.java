package com.jonjam.pinboard.common.service.provider;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import com.jonjam.pinboard.common.objectmodel.Reference;

public class ReferenceParamConverterProvider implements ParamConverterProvider {

    @Override
    public <T> ParamConverter<T> getConverter(
        final Class<T> rawType,
        final Type genericType,
        final Annotation[] annotations) {

        if (Reference.class.isAssignableFrom(rawType)) {
            try {
                final Method valueOfString = rawType.getDeclaredMethod(
                    "valueOf",
                    String.class);

                return new ParamConverter<>() {
                    @Override
                    public T fromString(final String value) {
                        return convertToType(value, rawType, valueOfString);
                    }

                    @Override
                    public String toString(final T value) {
                        return ((Reference) value).getValue();
                    }
                };
            } catch (final NoSuchMethodException e) {
                throw new RuntimeException(rawType.getName() + " implements Reference but does not supply valueOf method.", e);
            }
        } else {
            return null;
        }
    }

    private static <T> T convertToType(
        final String value,
        final Class<T> rawType,
        final Method valueMethod) {
        try {
            return rawType.cast(valueMethod.invoke(null, value));
        } catch (final Exception e) {
            throw new RuntimeException("Failed to convert string to " + value.getClass().getName() + " .", e);
        }
    }
}
