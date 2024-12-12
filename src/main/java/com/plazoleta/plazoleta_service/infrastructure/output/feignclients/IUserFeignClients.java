package com.plazoleta.plazoleta_service.infrastructure.output.feignclients;

import com.plazoleta.plazoleta_service.infrastructure.output.feignclients.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuarios-service", url = "localhost:8090/api/v1/user")
public interface IUserFeignClients {

    @GetMapping("/existsUserById/{id}")
    Boolean existsUserById(@PathVariable(value = "id") Long userId);

    @GetMapping("/{id}")
    UserDto getUserById(@PathVariable(value = "id") Long userId);

    @GetMapping("/email/{email}")
    UserDto getUserByEmail(@PathVariable(value = "email") String email);
}
