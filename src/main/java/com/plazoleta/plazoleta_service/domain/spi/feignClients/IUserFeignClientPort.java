package com.plazoleta.plazoleta_service.domain.spi.feignClients;

import com.plazoleta.plazoleta_service.domain.model.User;

public interface IUserFeignClientPort {
    Boolean existUserById(Long id);
    User getUserById(Long id);
    User getUserByEmail(String email);
}
