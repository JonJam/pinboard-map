package com.jonjam.pinboard.common.service.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.jonjam.pinboard.common.objectmodel.Immutable;

//public class RemoteExceptionDto {
//
//    private final String exceptionType;
//    private final JsonNode exceptionDetails;
//
//    @JsonCreator
//    public RemoteExceptionDto(
//        @JsonProperty("exceptionType") final String exceptionType,
//        @JsonProperty("exceptionDetails") final JsonNode exceptionDetails) {
//        this.exceptionType = exceptionType;
//        this.exceptionDetails = exceptionDetails;
//    }
//
//    public String getExceptionType() {
//        return exceptionType;
//    }
//
//    public JsonNode getExceptionDetails() {
//        return exceptionDetails;
//    }
//}

@Immutable
public abstract class RemoteExceptionDto {
    public abstract String getExceptionType();

    public abstract JsonNode getExceptionDetails();

    public static class Builder extends ImmutableRemoteExceptionDto.Builder {}
}
