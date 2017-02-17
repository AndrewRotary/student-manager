package com.practica.domain;

import java.sql.Date;

/**
 * Created by student on 2/7/2017.
 */
public class LibrarySubscription {
    private Long id;
    private Status status; // enum
    private Date startDate;
    private Date endDate;

    public LibrarySubscription() {
    }

    public LibrarySubscription(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
