package com.plazoleta.plazoleta_service.domain.spi.bearertoken;

public interface IToken {
    String getBearerToken();

    String getEmail(String token);

    Long getUserAuthenticationId(String token);
}
