package com.mycompany.tutorial;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @Column(name = "event_id", nullable = false)
    @GenericGenerator(name = "POOLED_LO_ID_GENERATOR", strategy = "enhanced-sequence", parameters = {
            @org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
            @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled-lo"),
            @org.hibernate.annotations.Parameter(name = "increment_size", value = "10"),
            @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000")
    })
    @GeneratedValue(generator = "POOLED_LO_ID_GENERATOR")
    private Long id;

    @Column(name = "title")
    private String title;

    @Temporal(TemporalType.TIMESTAMP)

    @Column(name = "event_date", columnDefinition = "timestamp with time zone", nullable = false, updatable = false, insertable = true)
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", updatable = false)
    private EventType type;

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    @Transient
    private boolean validEvent; //not reflected in DB

    public boolean isValidEvent() {
        return validEvent;
    }

    public void setValidEvent(boolean validEvent) {
        this.validEvent = validEvent;
    }

    public Event() {
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}