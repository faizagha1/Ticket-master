package com.EventService.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.EventService.Entities.Event;

public interface EventRepo extends JpaRepository<Event, Long> {
        @Query("Select e from Event e WHERE "
                        + "(:eventCategory IS NULL OR e.eventCategory = :eventCategory) "
                        + "AND (:eventLocation IS NULL OR e.eventLocation = :eventLocation) "
                        + "AND (:eventDate IS NULL OR e.eventDate = :eventDate) ")

        List<Event> searchEvents(
                        @Param("eventCategory") String eventCategory,
                        @Param("eventLocation") String eventLocation,
                        @Param("eventDate") String eventDate);
}
