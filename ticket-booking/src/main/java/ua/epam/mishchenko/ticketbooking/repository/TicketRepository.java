package ua.epam.mishchenko.ticketbooking.repository;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import ua.epam.mishchenko.ticketbooking.model.Category;
import ua.epam.mishchenko.ticketbooking.model.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, ObjectId> {
    Page<Ticket> getAllByUser(Pageable pageable, ObjectId userId);
    Page<Ticket> getAllByEvent(Pageable pageable, ObjectId eventId);
    Boolean existsByEventAndPlaceAndCategory(ObjectId eventId, Integer place, Category category);
}
