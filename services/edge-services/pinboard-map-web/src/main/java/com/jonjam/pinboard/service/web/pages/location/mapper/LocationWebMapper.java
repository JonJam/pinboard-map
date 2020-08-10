package com.jonjam.pinboard.service.web.pages.location.mapper;

import com.jonjam.pinboard.common.mapstruct.config.CommonMapperConfig;
import com.jonjam.pinboard.service.location.api.model.CreateLocationRequest;
import com.jonjam.pinboard.service.location.api.model.LocationDto;
import com.jonjam.pinboard.service.web.pages.location.model.CreateLocationWebRequest;
import com.jonjam.pinboard.service.web.pages.location.model.LocationWebModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    config = CommonMapperConfig.class,
    uses = { LocationStatusWebMapper.class }
)
public abstract class LocationWebMapper {

    @Mapping(source = "status", target = "status")
    public abstract CreateLocationRequest map(CreateLocationWebRequest request);

    @Mapping(source = "status", target = "status")
    public abstract LocationWebModel map(LocationDto location);
}
