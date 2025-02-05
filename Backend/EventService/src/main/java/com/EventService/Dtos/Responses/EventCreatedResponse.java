package com.EventService.Dtos.Responses;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventCreatedResponse {
    private Long eventId;
    private String eventName;
    private String eventDescription;
    private String eventImage;
    private String eventLocation;
    private String eventDate;
    private LocalDateTime createdAt;
    private Long createdBy;
    private String createdMessage;
}
