package jqwik.example.todo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class TodoRepositoryTest {

    @Autowired
    TodoRepository todoRepository;

    @Test
    public void found() {

        assertFalse(todoRepository.findById(1001L).isEmpty());
    }

    @Test
    public void notfound() {

        assertTrue(todoRepository.findById(1374L).isEmpty());
    }
}