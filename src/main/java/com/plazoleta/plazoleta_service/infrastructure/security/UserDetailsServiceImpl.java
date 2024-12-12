package com.plazoleta.plazoleta_service.infrastructure.security;



import com.plazoleta.plazoleta_service.infrastructure.output.feignclients.IUserFeignClients;
import com.plazoleta.plazoleta_service.infrastructure.output.feignclients.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private  final IUserFeignClients IUserFeignClients;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto userDto = IUserFeignClients.getUserByEmail(email);
        return new UserDetailsImpl(userDto);
    }
}
