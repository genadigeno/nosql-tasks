package ua.epam.mishchenko.ticketbooking.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.epam.mishchenko.ticketbooking.model.Category;
import ua.epam.mishchenko.ticketbooking.model.Ticket;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, org.bson.types.ObjectId> {
    Page<Ticket> getAllByUserId(Pageable pageable, org.bson.types.ObjectId userId);
    Page<Ticket> getAllByEventId(Pageable pageable, org.bson.types.ObjectId eventId);
    Boolean existsByEventIdAndPlaceAndCategory(org.bson.types.ObjectId eventId, Integer place, Category category);
}
