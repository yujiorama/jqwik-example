package jqwik.example.todo;

import lombok.Data;

@Data
public class Todo {

    private final Long id;

    private final String title;

    private final String note;
}
