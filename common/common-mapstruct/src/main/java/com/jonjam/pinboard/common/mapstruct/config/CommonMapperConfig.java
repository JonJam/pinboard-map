package com.jonjam.pinboard.common.mapstruct.config;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
    componentModel = "jsr330",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    typeConversionPolicy = ReportingPolicy.ERROR,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
)
public class CommonMapperConfig {
}
