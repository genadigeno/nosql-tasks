1. data models in `./src/main/java/ua.epam.mishchenko.ticketbooking.model` package <br>

2. database migrations in `./src/main/java/ua.epam.mishchenko.ticketbooking.db.migration` package <br>
   mocked data for the models in `./src/main/resources/db/migration` directory <br>

3. aggregation script: `./src/main/java/ua.epam.mishchenko.ticketbooking.repository.EventRepository#groupByTitle()` <br>

test endpoint: `/events/title/Rock Concert?pageSize=10&pageNum=1`

