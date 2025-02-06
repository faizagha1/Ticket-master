package com.BookingService.Dtos.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CancelResponse {
    private Long bookingId;
    private String message;
}
