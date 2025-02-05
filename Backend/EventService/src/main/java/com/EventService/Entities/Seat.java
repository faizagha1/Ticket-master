package com.EventService.Entities;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    private Integer rowNumber;
    private Integer columnNumber;
    private Boolean isBooked;
}
