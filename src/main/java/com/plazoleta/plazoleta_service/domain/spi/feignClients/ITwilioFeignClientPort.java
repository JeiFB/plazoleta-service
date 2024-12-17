package com.plazoleta.plazoleta_service.domain.spi.feignClients;

import com.plazoleta.plazoleta_service.domain.model.SmsMessage;

public interface ITwilioFeignClientPort {
    void sendSmsMessage(SmsMessage smsMessage);
}
