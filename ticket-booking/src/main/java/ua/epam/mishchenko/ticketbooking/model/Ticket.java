package ua.epam.mishchenko.ticketbooking.model;

import org.bson.types.ObjectId;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.util.Objects;

@Document(collection = "tickets")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private org.bson.types.ObjectId id;

    @Field(name = "user")
    private ObjectId user;//Normalized modeling

    @Field(name = "event")
    private ObjectId event;//Normalized modeling

    @Field(name = "place")
    private Integer place;

    @Enumerated(EnumType.STRING)
    @Field(name = "category")
    private Category category;

    public Ticket() {
    }

    public Ticket(ObjectId id, ObjectId user, ObjectId event, int place, Category category) {
        this.id = id;
        this.user = user;
        this.event = event;
        this.place = place;
        this.category = category;
    }

    public Ticket(ObjectId user, ObjectId event, int place, Category category) {
        this.user = user;
        this.event = event;
        this.place = place;
        this.category = category;
    }

    public org.bson.types.ObjectId getId() {
        return id;
    }

    public void setId(org.bson.types.ObjectId id) {
        this.id = id;
    }

    public ObjectId getUser() {
        return user;
    }

    public void setUser(ObjectId user) {
        this.user = user;
    }

    public ObjectId getEvent() {
        return event;
    }

    public void setEvent(ObjectId event) {
        this.event = event;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) && Objects.equals(user, ticket.user) && Objects.equals(event, ticket.event)
                && Objects.equals(place, ticket.place) && category == ticket.category;
    }

    public int hashCode() {
        return Objects.hash(id, user, event, place, category);
    }

    public String toString() {
        return "{" +
                "'id' : " + id +
                ", 'userId' : " + user +
                ", 'eventId' : " + event +
                ", 'place' : " + place +
                ", 'category' : '" + category +
                "'}";
    }
}
