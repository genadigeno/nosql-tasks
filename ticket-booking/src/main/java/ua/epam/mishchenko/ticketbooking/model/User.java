package ua.epam.mishchenko.ticketbooking.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.mongodb.core.mapping.Document;
import org.hibernate.annotations.Cache;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//@Setter
//@Getter
@Document(collection = "users")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private org.bson.types.ObjectId id;

    @Field(name = "name")
    private String name;

    @Field(name = "email")
    private String email;

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final List<Ticket> tickets = new ArrayList<>();

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserAccount userAccount;

    public User() {
    }

    public User(org.bson.types.ObjectId id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public org.bson.types.ObjectId getId() {
        return id;
    }

    public void setId(org.bson.types.ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }

    @Override
    public String toString() {
        return "{" +
                "'id' : " + id +
                ", 'name' : '" + name + '\'' +
                ", 'email' : '" + email + '\'' +
                '}';
    }
}

