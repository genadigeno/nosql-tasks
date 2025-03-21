package ua.epam.mishchenko.ticketbooking.repository;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.epam.mishchenko.ticketbooking.model.Event;
import ua.epam.mishchenko.ticketbooking.model.EventsAggregate;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event, ObjectId> {
    Page<Event> getAllByTitle(Pageable pageable, String title);
    Page<Event> getAllByDate(Pageable pageable, Date day);
    Boolean existsByTitleAndDate(String title, Date date);

    @Aggregation(pipeline="{'$group': {'_id':'$title', 'count':{'$sum': 1}, 'average':  {'$avg': '$ticket_price'}}}")
    List<EventsAggregate> groupByTitle();
}
