package com.EventService.Dtos.Reqests;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventCreateRequest {
    private String eventName;
    private String eventDescription;
    private String eventImage;
    private String eventLocation;
    private String eventCategory;
    private Integer totalSeats;
    private Integer rows;
    private Integer columns;
    private Set<String> eventArtists;
    private String eventDate;
}
