package ua.epam.mishchenko.ticketbooking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigDecimal;
import org.hibernate.annotations.Cache;

//@Setter
//@Getter
@Entity
@Table(name = "user_accounts")
@Cacheable
//@NoArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;

//    @OneToOne
//    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "money", nullable = false)
    private BigDecimal money;

    public UserAccount() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public UserAccount(User user, BigDecimal money) {
        this.user = user;
        this.money = money;
    }

    public UserAccount(ObjectId id, User user, BigDecimal money) {
        this.id = id;
        this.user = user;
        this.money = money;
    }

}
