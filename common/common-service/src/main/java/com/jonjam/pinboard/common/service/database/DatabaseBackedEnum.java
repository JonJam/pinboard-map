package com.jonjam.pinboard.common.service.database;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

// TODO Make less TA-y
// TODO Move to own common project
public interface DatabaseBackedEnum {
    class Hidden {
        private static Map<Class<?>, Map<Integer, Object>> ENUM_MAPS = new ConcurrentHashMap<>();

        static <T extends DatabaseBackedEnum> Map<Integer, Object> computeMapForClass(final Class<T> clazz) {
            return Arrays.stream(clazz.getEnumConstants())
                         .collect(Collectors.toMap(DatabaseBackedEnum::getId, Function.identity()));
        }
    }

    int getId();

    String getDescription();

    static <T extends DatabaseBackedEnum> T fromDatabaseRepresentation(final Class<T> clazz, final Integer id) {
        final Map<Integer, Object> enumMapForClass = Hidden.ENUM_MAPS.computeIfAbsent(clazz, c -> Hidden.computeMapForClass(clazz));
        return Optional.ofNullable((T) enumMapForClass.get(id))
                       .orElseThrow(() -> new IllegalStateException(String.format("Unexpected id: %d", id)));
    }
}
