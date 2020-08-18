package jqwik.example.todo;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public Optional<Todo> findById(Long id) {

        return todoRepository.findById(id);
    }

    @Transactional
    public Todo save(TodoCreationRequest creationRequest) {

        var entity = new Todo(creationRequest.getTitle(), creationRequest.getNote());

        return todoRepository.save(entity);
    }
}
