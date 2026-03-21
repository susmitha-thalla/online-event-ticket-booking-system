package com.event.ticketbooking.repository;

import com.event.ticketbooking.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByApprovalStatus(String status);

    List<Event> findByCreatedBy(String email);

    List<Event> findByCategoryAndApprovalStatus(String category, String status);

    List<Event> findByLocationAndApprovalStatus(String location, String status);

    List<Event> findByCategoryAndLocationAndApprovalStatus(String category, String location, String status);

    List<Event> findByEventDateBetweenAndApprovalStatus(LocalDateTime start, LocalDateTime end, String status);
}