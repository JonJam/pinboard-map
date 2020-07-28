//package com.jonjam.pinboard.service.web.mapper;
//
//import com.jonjam.pinboard.common.mapstruct.config.CommonMapperConfig;
//import com.jonjam.pinboard.service.location.api.model.CreateLocationRequest;
//import com.jonjam.pinboard.service.location.api.model.LocationDto;
//import com.jonjam.pinboard.service.location.location.model.InsertLocationRequest;
//import com.jonjam.pinboard.service.location.location.model.Location;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//@Mapper(
//    config = CommonMapperConfig.class,
//    uses = { LocationStatusMapper.class }
//)
//public abstract class TestWebModelMapper {
//
//    @Mapping(source = "status", target = "locationStatus")
//    public abstract InsertLocationRequest map(CreateLocationRequest request);
//
//    @Mapping(source = "locationCode", target = "code")
//    @Mapping(source = "locationStatus", target = "status")
//    public abstract LocationDto map(Location location);
//}
