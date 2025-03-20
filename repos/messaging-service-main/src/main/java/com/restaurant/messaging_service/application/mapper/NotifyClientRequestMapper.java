package com.restaurant.messaging_service.application.mapper;

import com.restaurant.messaging_service.application.dto.NotifyClientRequestDto;
import com.restaurant.messaging_service.domain.model.NotifyClient;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel ="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface NotifyClientRequestMapper {

    NotifyClient toNotifyClient(NotifyClientRequestDto notifyClientRequestDto);
}
