package com.jonjam.pinboard.service.location.svc.mapper;

import com.jonjam.pinboard.common.mapstruct.config.CommonMapperConfig;
import com.jonjam.pinboard.service.location.api.model.LocationStatusDto;
import com.jonjam.pinboard.service.location.svc.dao.location.model.LocationStatus;
import org.mapstruct.Mapper;

@Mapper(
    config = CommonMapperConfig.class
)
public abstract class LocationStatusMapper {

    public abstract LocationStatus map(LocationStatusDto statusDto);

    public abstract LocationStatusDto map(LocationStatus status);
}
