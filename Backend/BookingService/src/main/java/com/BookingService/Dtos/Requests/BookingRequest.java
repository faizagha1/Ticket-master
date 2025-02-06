package com.BookingService.Dtos.Requests;



import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingRequest {
    private Long userId;
    private Long eventId;
    private Integer row;
    private Integer column;
    private Double ticketPrice;
}
