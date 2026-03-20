package com.event.ticketbooking.dto;

import java.time.LocalDateTime;

public class EventRequest {

    private String title;
    private String description;
    private String location;
    private String category; // ✅ ADD THIS
    private LocalDateTime eventDate;
    private Double price;
    private Integer availableSeats;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getCategory() { return category; }  // ✅ IMPORTANT
    public void setCategory(String category) { this.category = category; }

    public LocalDateTime getEventDate() { return eventDate; }
    public void setEventDate(LocalDateTime eventDate) { this.eventDate = eventDate; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(Integer availableSeats) { this.availableSeats = availableSeats; }
}