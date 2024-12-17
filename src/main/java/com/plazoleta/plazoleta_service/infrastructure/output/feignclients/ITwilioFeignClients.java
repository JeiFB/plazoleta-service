package com.plazoleta.plazoleta_service.infrastructure.output.feignclients;

import com.plazoleta.plazoleta_service.application.dtos.request.SmsMessageRequestDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "mensajeria-service", url = "localhost:8070/api/v1/sms/message")
public interface ITwilioFeignClients {

    @PostMapping("/")
    ResponseEntity<Void> sendSmsMessage(@Valid @RequestBody SmsMessageRequestDto smsMessageRequestDto);
}
