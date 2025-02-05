package com.EventService.Dtos.Reqests;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventUpdateRequest {
    private String eventName;
    private String eventDescription;
    private String eventImage;
    private String eventLocation;
    private String eventDate;
    private List<String> eventArtists;
}
