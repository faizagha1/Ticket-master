package com.EventService.Dtos.Responses;

import java.util.List;
import java.util.Set;

import com.EventService.Entities.Seat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventDetailsResponse {
    private String eventName;
    private String eventDescription;
    private String eventImage;
    private String eventLocation;
    private Set<String> eventArtists;
    private String eventCategory;
    private List<Seat> eventSeats;
    private String eventDate;
}
