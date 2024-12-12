package com.plazoleta.plazoleta_service.infrastructure.feignconfiguration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class SecurityFeignRequestInterceptor implements RequestInterceptor {
    private static  final  String AUTHORIZATION_HEADER = "Authorization";
    @Override
    public void apply(RequestTemplate requestTemplate) {
         requestTemplate.header(AUTHORIZATION_HEADER, getBearerTokenHeader());
    }

    public static String getBearerTokenHeader(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
    }
}
