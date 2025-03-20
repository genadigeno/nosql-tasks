package ua.epam.mishchenko.ticketbooking.service;

import org.bson.types.ObjectId;
import ua.epam.mishchenko.ticketbooking.model.UserAccount;

import java.math.BigDecimal;

public interface UserAccountService {
    UserAccount refillAccount(ObjectId userId, BigDecimal money);
}
