package com.plazoleta.plazoleta_service.infrastructure.output.feignclients.adapter;

import com.plazoleta.plazoleta_service.application.dtos.request.SmsMessageRequestDto;
import com.plazoleta.plazoleta_service.domain.model.SmsMessage;
import com.plazoleta.plazoleta_service.domain.spi.feignClients.ITwilioFeignClientPort;
import com.plazoleta.plazoleta_service.infrastructure.output.feignclients.ITwilioFeignClients;
import com.plazoleta.plazoleta_service.infrastructure.output.feignclients.mapper.ITwilioMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TwilioFeignAdapter implements ITwilioFeignClientPort {
    private final ITwilioFeignClients twilioFeignClients;
    private final ITwilioMapper twilioMapper;


    @Override
    public void sendSmsMessage(SmsMessage smsMessage) {
        SmsMessageRequestDto smsMessageRequestDto = twilioMapper.toSmsRequest(smsMessage);
        twilioFeignClients.sendSmsMessage(smsMessageRequestDto);
    }
}
