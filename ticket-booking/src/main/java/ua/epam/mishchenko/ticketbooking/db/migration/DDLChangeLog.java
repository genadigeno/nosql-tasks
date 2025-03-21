package ua.epam.mishchenko.ticketbooking.db.migration;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ua.epam.mishchenko.ticketbooking.db.migration.Collections.*;

@ChangeLog(order = "001")
public class DDLChangeLog {
    private final static Logger log = LoggerFactory.getLogger(DDLChangeLog.class);

    @ChangeSet(order = "001", id = "create_events_collection", author = "Geno")
    public void createEventsCollection(MongockTemplate mongockTemplate) {
        if (!mongockTemplate.collectionExists(EVENTS_COLLECTION)) {
            mongockTemplate.createCollection(EVENTS_COLLECTION);
            log.info("Collection '{}' created successfully.", EVENTS_COLLECTION);
        }
    }

    @ChangeSet(order = "002", id = "create_tickets_collection", author = "Geno")
    public void createTicketsCollection(MongockTemplate mongockTemplate) {
        if (!mongockTemplate.collectionExists(TICKETS_COLLECTION)) {
            mongockTemplate.createCollection(TICKETS_COLLECTION);
            log.info("Collection '{}' created successfully.", TICKETS_COLLECTION);
        }
    }

    @ChangeSet(order = "003", id = "create_users_collection", author = "Geno")
    public void createUsersCollection(MongockTemplate mongockTemplate) {
        if (!mongockTemplate.collectionExists(USERS_COLLECTION)) {
            mongockTemplate.createCollection(USERS_COLLECTION);
            log.info("Collection '{}' created successfully.", USERS_COLLECTION);
        }
    }

    @ChangeSet(order = "004", id = "create_user_accounts_collection", author = "Geno")
    public void createUserAccountsCollection(MongockTemplate mongockTemplate) {
        if (!mongockTemplate.collectionExists(USER_ACCOUNTS_COLLECTION)) {
            mongockTemplate.createCollection(USER_ACCOUNTS_COLLECTION);
            log.info("Collection '{}' created successfully.", USER_ACCOUNTS_COLLECTION);
        }
    }
}
