package com.BookingService.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.BookingService.Dtos.Requests.BookingRequest;
import com.BookingService.Repositories.BookingRepo;
import com.BookingService.Dtos.Response.*;
import com.BookingService.Entities.Booking;
import com.BookingService.Entities.Status;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {
        private final BookingRepo bookingRepo;

        public BookingResponse book(BookingRequest bookingRequest) {
                Booking booking = Booking
                                .builder()
                                .userId(bookingRequest.getUserId())
                                .eventId(bookingRequest.getEventId())
                                .seatRow(bookingRequest.getRow())
                                .seatColumn(bookingRequest.getColumn())
                                .totalAmount(1 * bookingRequest.getTicketPrice())
                                .status(Status.PENDING)
                                .build();
                bookingRepo.save(booking);
                return BookingResponse.builder()
                                .bookingId(booking.getBookingId())
                                .userId(booking.getUserId())
                                .eventId(booking.getEventId())
                                .row(booking.getSeatRow())
                                .column(booking.getSeatColumn())
                                .numberOfTickets(1)
                                .totalAmount(booking.getTotalAmount())
                                .status(booking.getStatus().toString())
                                .build();
        }

        public List<BookingResponse> getBookings(Long userId) {
                List<Booking> bookings = bookingRepo.findByUserId(userId);
                return bookings.stream()
                                .map(booking -> BookingResponse.builder()
                                                .bookingId(booking.getBookingId())
                                                .userId(booking.getUserId())
                                                .eventId(booking.getEventId())
                                                .row(booking.getSeatRow())
                                                .column(booking.getSeatColumn())
                                                .totalAmount(booking.getTotalAmount())
                                                .status(booking.getStatus().toString())
                                                .build())
                                .collect(Collectors.toList());
        }

        public BookingResponse getBookingById(Long bookingId) {
                Booking booking = bookingRepo.findById(bookingId)
                                .orElseThrow(() -> new RuntimeException("Booking not found"));
                return BookingResponse.builder()
                                .bookingId(booking.getBookingId())
                                .userId(booking.getUserId())
                                .eventId(booking.getEventId())
                                .row(booking.getSeatRow())
                                .column(booking.getSeatColumn())
                                .totalAmount(booking.getTotalAmount())
                                .status(booking.getStatus().toString())
                                .build();
        }

        public CancelResponse cancelBooking(Long bookingId) {
                Booking booking = bookingRepo.findById(bookingId)
                                .orElseThrow(() -> new RuntimeException("Booking not found"));
                booking.setStatus(Status.CANCELLED);
                booking.setCancelTime(LocalDateTime.now());
                bookingRepo.save(booking);
                return CancelResponse.builder()
                                .bookingId(booking.getBookingId())
                                .message("Booking cancelled successfully")
                                .build();
        }
}
