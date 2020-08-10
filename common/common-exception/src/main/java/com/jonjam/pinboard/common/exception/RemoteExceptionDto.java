package com.jonjam.pinboard.common.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jonjam.pinboard.common.objectmodel.Immutable;

@Immutable
@JsonDeserialize(builder = RemoteExceptionDto.Builder.class)
public abstract class RemoteExceptionDto {
    public abstract String getExceptionType();

    public abstract JsonNode getExceptionDetails();

    public static class Builder extends ImmutableRemoteExceptionDto.Builder { }
}
