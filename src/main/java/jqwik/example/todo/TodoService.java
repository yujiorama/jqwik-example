package jqwik.example.todo;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public Optional<Todo> findById(Long id) {

        return todoRepository.findById(id);
    }
}
