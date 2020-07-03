package com.jonjam.pinboard.common.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.jonjam.pinboard.common.objectmodel.Immutable;

@Immutable
public abstract class RemoteExceptionDto {
    public abstract String getExceptionType();

    public abstract JsonNode getExceptionDetails();

    public static class Builder extends ImmutableRemoteExceptionDto.Builder {}
}
