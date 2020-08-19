package org.bitbucket.yujiorama.jqwik.example.todo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodoCreationRequest {

    private String title;

    private String note;
}
