package com.EventService.Dtos.Responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventDeletedResponse {
    private Long eventId;
    private String deletedMessage;
}
