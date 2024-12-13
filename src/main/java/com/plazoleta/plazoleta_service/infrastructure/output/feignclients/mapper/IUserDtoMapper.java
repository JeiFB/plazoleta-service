package com.plazoleta.plazoleta_service.infrastructure.output.feignclients.mapper;

import com.plazoleta.plazoleta_service.domain.model.User;
import com.plazoleta.plazoleta_service.infrastructure.output.feignclients.dto.UserDto;
import org.mapstruct.Mapper;

import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserDtoMapper {
    User toUserModel(UserDto userDto);


}
