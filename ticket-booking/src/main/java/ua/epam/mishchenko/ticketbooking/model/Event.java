package ua.epam.mishchenko.ticketbooking.model;

import org.bson.types.ObjectId;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Cacheable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static ua.epam.mishchenko.ticketbooking.utils.Constants.DATE_FORMATTER;

@Document(collection = "events")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;

    @Field(name = "title")
    private String title;

    @Field(name = "date")
    private Date date;

    @Field(name = "ticket_price")
    private BigDecimal ticketPrice;

    @Field(name = "tickets")
    private List<ObjectId> tickets = new ArrayList<>();

    public Event() {
    }

    public Event(String title, Date date, BigDecimal ticketPrice) {
        this.title = title;
        this.date = date;
        this.ticketPrice = ticketPrice;
    }

    public Event(ObjectId id, String title, Date date, BigDecimal ticketPrice) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.ticketPrice = ticketPrice;
    }

    /*public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
        ticket.setEvent(this);
    }*/

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public List<ObjectId> getTickets() {
        return tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) && Objects.equals(title, event.title)
                && Objects.equals(date, event.date) && Objects.equals(ticketPrice, event.ticketPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, date, ticketPrice);
    }

    @Override
    public String toString() {
        return "{" +
                "'id' : " + id +
                ", 'title' : '" + title + '\'' +
                ", 'date' : '" + DATE_FORMATTER.format(date) +
                "', 'ticket_price' : " + ticketPrice +
                "}";
    }
}
