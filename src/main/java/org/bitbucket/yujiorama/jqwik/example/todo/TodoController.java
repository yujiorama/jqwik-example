package org.bitbucket.yujiorama.jqwik.example.todo;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "todo")
@AllArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Todo get(@PathVariable Long id) {

        return todoService.findById(id) //
                .orElseThrow(NotFoundException::new);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Todo> put(@RequestBody TodoCreationRequest creationRequest) {

        return new ResponseEntity<>(todoService.save(creationRequest), HttpStatus.CREATED);
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "not found")
    public static class NotFoundException extends RuntimeException {
        private static final long serialVersionUID = 5831188421609220646L;
    }
}
