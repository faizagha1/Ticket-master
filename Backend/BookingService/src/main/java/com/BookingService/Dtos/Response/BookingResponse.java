package com.BookingService.Dtos.Response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingResponse {
    private Long bookingId;
    private Long userId;
    private Long eventId;
    private Integer row;
    private Integer column;
    private Integer numberOfTickets;
    private Double totalAmount;
    private String status;
}
