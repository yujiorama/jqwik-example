package org.bitbucket.yujiorama.jqwik.example.todo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor_ = {@PersistenceConstructor})
public class Todo {

    @Id
    @With
    private final Long id;

    @With
    private final String title;

    @With
    private final String note;

    public Todo(String title, String note) {
        this.id = null;
        this.title = title;
        this.note = note;
    }
}
