package com.EventService.Controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EventService.Dtos.Reqests.EventCreateRequest;
import com.EventService.Dtos.Reqests.EventUpdateRequest;
import com.EventService.Services.EventService;

import lombok.RequiredArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<?> createEvent(
            @RequestBody EventCreateRequest eventRequest) {
        try {
            return ResponseEntity.ok(eventService.createEvent(eventRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<?> getParticularEventDetails(@PathVariable Long eventId) {
        try {
            return ResponseEntity.ok(eventService.getParticularEventDetails(eventId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllEvents() {
        try {
            return ResponseEntity.ok(eventService.getAllEvents());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchEvents(
            @RequestParam(value = "eventCategory", required = false) String eventCategory,
            @RequestParam(value = "eventLocation", required = false) String eventLocation,
            @RequestParam(value = "eventDate", required = false) String eventDate) {
        try {
            return ResponseEntity.ok(eventService.searchEvents(eventCategory, eventLocation, eventDate));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<?> updateEvent(@PathVariable Long eventId,
            @RequestBody EventUpdateRequest eventUpdateRequest) {
        try {
            return ResponseEntity.ok(eventService.updateEvent(eventId, eventUpdateRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long eventId) {
        try {
            return ResponseEntity.ok(eventService.deleteEvent(eventId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
