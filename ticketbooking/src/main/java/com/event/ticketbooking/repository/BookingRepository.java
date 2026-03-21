package com.event.ticketbooking.repository;

import com.event.ticketbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUser_Email(String email);

    List<Booking> findByEvent_CreatedBy(String email);
}