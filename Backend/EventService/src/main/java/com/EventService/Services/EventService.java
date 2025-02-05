package com.EventService.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.EventService.Dtos.Reqests.EventCreateRequest;
import com.EventService.Dtos.Reqests.EventUpdateRequest;
import com.EventService.Dtos.Responses.EventCreatedResponse;
import com.EventService.Dtos.Responses.EventDeletedResponse;
import com.EventService.Dtos.Responses.EventDetailsResponse;
import com.EventService.Dtos.Responses.EventUpdatedResponse;
import com.EventService.Entities.Event;
import com.EventService.Entities.Seat;
import com.EventService.Repository.EventRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepo eventRepo;

    public EventCreatedResponse createEvent(EventCreateRequest eventRequest) {
        List<Seat> eventSeats = new ArrayList<>();
        for (int i = 0; i < eventRequest.getRows(); i++) {
            for (int j = 0; j < eventRequest.getColumns(); j++) {
                Seat seat = new Seat(i, j, false);
                eventSeats.add(seat);
            }
        }
        Event event = Event
                .builder()
                .eventName(eventRequest.getEventName())
                .eventDescription(eventRequest.getEventDescription())
                .eventImage(eventRequest.getEventImage())
                .eventLocation(eventRequest.getEventLocation())
                .eventDate(eventRequest.getEventDate())
                .eventArtists(eventRequest.getEventArtists())
                .eventCategory(eventRequest.getEventCategory())
                .eventSeats(eventSeats)
                .build();

        eventRepo.save(event);
        return EventCreatedResponse
                .builder()
                .eventId(event.getEventId())
                .eventName(event.getEventName())
                .eventDescription(event.getEventDescription())
                .eventImage(event.getEventImage())
                .eventLocation(event.getEventLocation())
                .eventDate(event.getEventDate())
                .createdAt(event.getCreatedAt())
                // .createdBy(event.getCreatedBy())
                .createdMessage("Event created successfully")
                .build();
    }

    public EventDeletedResponse deleteEvent(Long eventId) {
        Event event = eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        eventRepo.delete(event);
        String deletedMessage = "Event with id " + eventId + " deleted successfully";
        return EventDeletedResponse
                .builder()
                .eventId(event.getEventId())
                .deletedMessage(deletedMessage)
                .build();
    }

    public EventUpdatedResponse updateEvent(Long eventId, EventUpdateRequest eventUpdateRequest) {
        Event event = eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        List<String> changes = new ArrayList<>();
        if (eventUpdateRequest.getEventName() != null) {
            event.setEventName(eventUpdateRequest.getEventName());
            changes.add("name from " + event.getEventName() + " to " + eventUpdateRequest.getEventName());
        }
        if (eventUpdateRequest.getEventDescription() != null) {
            event.setEventDescription(eventUpdateRequest.getEventDescription());
            changes.add("description from " + event.getEventDescription() + " to "
                    + eventUpdateRequest.getEventDescription());
        }
        if (eventUpdateRequest.getEventImage() != null) {
            event.setEventImage(eventUpdateRequest.getEventImage());
            changes.add("image from " + event.getEventImage() + " to " + eventUpdateRequest.getEventImage());
        }
        if (eventUpdateRequest.getEventLocation() != null) {
            event.setEventLocation(eventUpdateRequest.getEventLocation());
            changes.add("location from " + event.getEventLocation() + " to " + eventUpdateRequest.getEventLocation());
        }
        if (eventUpdateRequest.getEventDate() != null) {
            event.setEventDate(eventUpdateRequest.getEventDate());
            changes.add("date from " + event.getEventDate() + " to " + eventUpdateRequest.getEventDate());
        }
        if (eventUpdateRequest.getEventArtists() != null) {
            event.getEventArtists().addAll(eventUpdateRequest.getEventArtists());
            changes.add("artists from " + event.getEventArtists() + " to " + eventUpdateRequest.getEventArtists());
        }
        eventRepo.save(event);
        String eventResponse = "Event with id " + eventId + " updated successfully: " + String.join(", ", changes);
        return EventUpdatedResponse
                .builder()
                .eventId(event.getEventId())
                .updatedMessage(eventResponse)
                .build();
    }

    public EventDetailsResponse getParticularEventDetails(Long eventId) {
        Event event = eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        return EventDetailsResponse.builder()
                .eventName(event.getEventName())
                .eventDescription(event.getEventDescription())
                .eventImage(event.getEventImage())
                .eventLocation(event.getEventLocation())
                .eventDate(event.getEventDate())
                .eventArtists(event.getEventArtists())
                .eventCategory(event.getEventCategory())
                .eventSeats(event.getEventSeats())
                .build();

    }

    public List<EventDetailsResponse> getAllEvents() {
        List<EventDetailsResponse> eventDetails = new ArrayList<>();
        List<Event> events = eventRepo.findAll();
        events.stream()
                .map(event -> EventDetailsResponse.builder()
                        .eventName(event.getEventName())
                        .eventDescription(event.getEventDescription())
                        .eventImage(event.getEventImage())
                        .eventLocation(event.getEventLocation())
                        .eventDate(event.getEventDate())
                        .eventArtists(event.getEventArtists())
                        .eventCategory(event.getEventCategory())
                        .eventSeats(event.getEventSeats())
                        .build())
                .forEach(event -> eventDetails.add(event));
        return eventDetails;
    }

    public List<EventDetailsResponse> searchEvents(String eventCategory, String eventLocation,
            String eventDate) {
        List<EventDetailsResponse> eventDetailsFiltered = new ArrayList<>();
        List<Event> events = eventRepo.searchEvents(eventCategory, eventLocation, eventDate);
        events.forEach(event -> System.out.println(event.toString()));
        events.stream()
                .map(event -> EventDetailsResponse.builder()
                        .eventName(event.getEventName())
                        .eventDescription(event.getEventDescription())
                        .eventImage(event.getEventImage())
                        .eventLocation(event.getEventLocation())
                        .eventDate(event.getEventDate())
                        .eventArtists(event.getEventArtists())
                        .eventCategory(event.getEventCategory())
                        .eventSeats(event.getEventSeats())
                        .build())
                .forEach(event -> eventDetailsFiltered.add(event));
        return eventDetailsFiltered;
    }

}
