package com.EventService.Dtos.Responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventUpdatedResponse {
    private Long eventId;
    private String updatedMessage;
}
