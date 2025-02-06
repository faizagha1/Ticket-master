package com.BookingService.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BookingService.Entities.Booking;

public interface BookingRepo extends JpaRepository<Booking, Long> {

    List<Booking> findByUserId(Long userId);

}
