package jqwik.example.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class TodoServiceTest {

    @Autowired
    TodoService todoService;

    @Test
    public void found() {

        var actual = todoService.findById(1001L);

        assertFalse(actual.isEmpty());
        assertEquals(1001L, actual.get().getId());
    }

    @Test
    public void notfound() {

        var actual = todoService.findById(1374L);

        assertTrue(actual.isEmpty());
    }

    @Test
    public void saved() {

        var creationRequest = new TodoCreationRequest();
        creationRequest.setTitle("title");
        creationRequest.setNote("note");

        var actual = todoService.save(creationRequest);

        assertNotNull(actual.getId());
        assertEquals(creationRequest.getTitle(), actual.getTitle());
        assertEquals(creationRequest.getNote(), actual.getNote());
    }
}