package taskmanager.model;

import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.PrePersist;
import lombok.*;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

@Entity("tasks")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    private ObjectId id;
    private LocalDateTime created;
    private LocalDateTime deadline;
    private String name;
    private String description;
    private Category category;

//    @Embedded
    private List<SubTask> subtasks;

    @PrePersist
    public void prePersist() {
        if (created == null) {
            created = LocalDateTime.now();
        }
        if (deadline == null) {
            deadline = LocalDateTime.now().plusDays(1);
        }
    }

    @Override
    public String toString() {
        return "[id=" + id + ", name=" + name + ", category=" + category + "]";
    }
}
