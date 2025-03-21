package ua.epam.mishchenko.ticketbooking.db.migration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ua.epam.mishchenko.ticketbooking.db.migration.Collections.*;

@ChangeLog(order = "002")
public class TicketsAndEventsDataMigration {

    @ChangeSet(order = "001", id = "insert_events_data_with_tickets", author = "Geno")
    public void insertEventsDataWithTickets(MongockTemplate mongoTemplate) throws IOException {
        // Load JSON file
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode eventsJsonData = objectMapper.readTree(
                new ClassPathResource("db/migration/events.json").getInputStream());

        // Insert Events
        MongoCollection<Document> eventsCollection = mongoTemplate.getCollection(EVENTS_COLLECTION);
        List<Document> eventDocuments = new ArrayList<>();
        for (JsonNode eventNode : eventsJsonData) {
            eventDocuments.add(Document.parse(eventNode.toString()));
        }
        eventsCollection.insertMany(eventDocuments);
    }

    @ChangeSet(order = "002", id = "insert_tickets_data", author = "Geno")
    public void insertTicketsData(MongockTemplate mongoTemplate) throws IOException {
        // Load JSON file
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode ticketJsonData = objectMapper.readTree(
                new ClassPathResource("db/migration/tickets.json").getInputStream());

        // Insert Tickets
        MongoCollection<Document> ticketsCollection = mongoTemplate.getCollection(TICKETS_COLLECTION);
        List<Document> ticketDocuments = new ArrayList<>();
        for (JsonNode ticketNode : ticketJsonData) {
            ticketDocuments.add(Document.parse(ticketNode.toString()));
        }
        ticketsCollection.insertMany(ticketDocuments);
    }

    @ChangeSet(order = "003", id = "insert_extra_events_data", author = "Geno")
    public void insertExtraEventsData(MongockTemplate mongoTemplate) throws IOException {
        // Load JSON file
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode eventsJsonData = objectMapper.readTree(
                new ClassPathResource("db/migration/extra_events.json").getInputStream());

        // Insert Events
        MongoCollection<Document> eventsCollection = mongoTemplate.getCollection(EVENTS_COLLECTION);
        List<Document> eventDocuments = new ArrayList<>();
        for (JsonNode eventNode : eventsJsonData) {
            eventDocuments.add(Document.parse(eventNode.toString()));
        }
        eventsCollection.insertMany(eventDocuments);
    }
}
