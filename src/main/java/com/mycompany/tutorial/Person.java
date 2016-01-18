package com.mycompany.tutorial;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "POOLED_LO_ID_GENERATOR", strategy = "enhanced-sequence", parameters = {
            @org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
            @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled-lo"),
            @org.hibernate.annotations.Parameter(name = "increment_size", value = "10"),
            @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000")
    })
    @GeneratedValue(generator = "POOLED_LO_ID_GENERATOR")

    private Long id;
    @Column(name = "age")
    private int age;
    @Column(name = "firstname")
    private String firstname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Column(name = "lastname")
    private String lastname;

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @ManyToMany
    @JoinTable(name = "person_event", joinColumns = {
            @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false) }, inverseJoinColumns = {
                    @JoinColumn(name = "event_id", referencedColumnName = "event_id", nullable = false) })
    private Set<Event> events = new HashSet<Event>();

    public Set<Event> getEvents() {
        return events;
    }

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstname = firstName;
        this.lastname = lastName;
    }

    // Accessor methods for all properties, private setter for 'id'

}