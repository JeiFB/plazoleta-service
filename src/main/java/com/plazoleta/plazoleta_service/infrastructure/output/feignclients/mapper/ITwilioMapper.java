package com.plazoleta.plazoleta_service.infrastructure.output.feignclients.mapper;

import com.plazoleta.plazoleta_service.application.dtos.request.SmsMessageRequestDto;
import com.plazoleta.plazoleta_service.domain.model.SmsMessage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ITwilioMapper {
    SmsMessageRequestDto toSmsRequest(SmsMessage smsMessage);
}
