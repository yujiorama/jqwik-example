package jqwik.example.todo;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__(@PersistenceConstructor))
public class Todo {

    @Id
    @With
    private final Long id;

    private final String title;

    private final String note;

    public Todo(String title, String note) {
        this.id = null;
        this.title = title;
        this.note = note;
    }
}
