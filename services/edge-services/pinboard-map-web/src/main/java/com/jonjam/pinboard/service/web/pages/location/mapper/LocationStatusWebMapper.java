package com.jonjam.pinboard.service.web.pages.location.mapper;

import com.jonjam.pinboard.common.mapstruct.config.CommonMapperConfig;
import com.jonjam.pinboard.service.location.api.model.LocationStatusDto;
import com.jonjam.pinboard.service.web.pages.location.model.LocationStatusWebModel;
import org.mapstruct.Mapper;

@Mapper(
    config = CommonMapperConfig.class
)
public abstract class LocationStatusWebMapper {

    public abstract LocationStatusDto map(LocationStatusWebModel status);

    public abstract LocationStatusWebModel map(LocationStatusDto status);
}
