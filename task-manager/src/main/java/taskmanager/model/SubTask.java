package taskmanager.model;

import dev.morphia.annotations.Embedded;
import lombok.*;

@Embedded
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubTask {
    private String name;
    private String description;
}
