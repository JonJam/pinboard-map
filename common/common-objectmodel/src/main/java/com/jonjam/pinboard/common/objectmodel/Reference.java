package com.jonjam.pinboard.common.objectmodel;

import com.fasterxml.jackson.annotation.JsonValue;

public abstract class Reference {

    @JsonValue
    public abstract String getValue();

    @Override
    public final String toString() {
        return getValue();
    }
}