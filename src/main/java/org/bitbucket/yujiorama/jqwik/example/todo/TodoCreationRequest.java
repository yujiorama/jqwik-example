package org.bitbucket.yujiorama.jqwik.example.todo;

import lombok.*;

@Data
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoCreationRequest {

    @With
    @NonNull
    private final String title;

    @With
    private String note;
}
