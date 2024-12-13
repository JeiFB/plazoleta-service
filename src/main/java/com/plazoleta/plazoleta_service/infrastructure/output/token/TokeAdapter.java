package com.plazoleta.plazoleta_service.infrastructure.output.token;


import com.plazoleta.plazoleta_service.domain.spi.bearertoken.IToken;
import com.plazoleta.plazoleta_service.infrastructure.security.TokenUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class TokeAdapter implements IToken {

    @Override
    public String getBearerToken() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
    }

    @Override
    public String getEmail(String token) {
        return TokenUtils.getEmail(token.replace("Bearer ",""));
    }

    @Override
    public Long getUserAuthenticationId(String token) {
        return TokenUtils.getUserAuthenticationId(token.replace("Bearer ",""));
    }
}
