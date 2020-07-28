package com.jonjam.pinboard.service.location.api.ref;

import com.jonjam.pinboard.common.objectmodel.Immutable;
import com.jonjam.pinboard.common.objectmodel.Reference;
import org.apache.commons.lang3.StringUtils;

@Immutable
public abstract class LocationCode extends Reference {
    // Required by ReferenceParamConverterProvider
    public static LocationCode valueOf(final String value) {
        return ImmutableLocationCode.builder()
                                    .withValue(StringUtils.trim(value))
                                    .build();
    }

    public static LocationCode valueOf(final long value) {
        return ImmutableLocationCode.builder()
                                    .withValue(String.valueOf(value))
                                    .build();
    }
}
