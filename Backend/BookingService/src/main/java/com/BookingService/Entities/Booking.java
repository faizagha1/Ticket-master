package com.BookingService.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookingId;

    private Long userId;

    private Long eventId;

    private Integer seatRow;

    private Integer seatColumn;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Double totalAmount;

    private LocalDateTime bookingTime;
    private LocalDateTime cancelTime;

    @PrePersist
    public void prePersist() {
        this.bookingTime = LocalDateTime.now();
    }

}
