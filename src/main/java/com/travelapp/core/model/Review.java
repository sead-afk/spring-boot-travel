package com.travelapp.core.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Review {

    private Long id;
    private User user;
    private String type; // e.g., DESTINATION, HOTEL
    private Long referenceId; // ID of the destination or hotel being reviewed
    private int rating;
    private String comment;
    private LocalDate reviewDate;

    public Review(Long id, User user, String type, Long referenceId, int rating, String comment, LocalDate reviewDate) {
        this.id = id;
        this.user = user;
        this.type = type;
        this.referenceId = referenceId;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }
}
