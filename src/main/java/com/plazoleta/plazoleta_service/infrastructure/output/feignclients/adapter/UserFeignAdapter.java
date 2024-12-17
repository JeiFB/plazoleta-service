package com.plazoleta.plazoleta_service.infrastructure.output.feignclients.adapter;


import com.plazoleta.plazoleta_service.domain.model.User;
import com.plazoleta.plazoleta_service.domain.spi.feignClients.IUserFeignClientPort;
import com.plazoleta.plazoleta_service.infrastructure.output.feignclients.IUserFeignClients;
import com.plazoleta.plazoleta_service.infrastructure.output.feignclients.dto.UserDto;
import com.plazoleta.plazoleta_service.infrastructure.output.feignclients.mapper.IUserDtoMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserFeignAdapter implements IUserFeignClientPort {

    private final IUserFeignClients IUserFeignClients;

    private final IUserDtoMapper userDtoMapper;

    @Override
    public Boolean existUserById(Long id) {
        return IUserFeignClients.existsUserById(id);
    }

    @Override
    public User getUserById(Long id) {
        UserDto userDto = IUserFeignClients.getUserById(id);
        return userDtoMapper.toUserModel(userDto);
    }

    @Override
    public User getUserByEmail(String email) {
        UserDto userDto= IUserFeignClients.getUserByEmail(email);
        return userDtoMapper.toUserModel(userDto);
}
}
