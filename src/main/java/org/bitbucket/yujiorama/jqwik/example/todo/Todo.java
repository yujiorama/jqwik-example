package org.bitbucket.yujiorama.jqwik.example.todo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor_ = {@PersistenceConstructor})
public class Todo {

    @Id
    private Long id;

    @With
    @NonNull
    private final String title;

    @With
    private String note;
}
