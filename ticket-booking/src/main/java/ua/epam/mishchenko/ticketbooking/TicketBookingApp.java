package ua.epam.mishchenko.ticketbooking;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMongock
public class TicketBookingApp {
    public static void main(String[] args) {
        SpringApplication.run(TicketBookingApp.class, args);
    }
}
