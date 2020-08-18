package jqwik.example.todo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "todo")
@AllArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Todo getTodo(@PathVariable Long id) {

        return todoService.findById(id) //
                .orElseThrow(() -> new NotFoundException());
    }
}
