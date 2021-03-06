package org.bitbucket.yujiorama.jqwik.example.todo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@TestPropertySources({
        @TestPropertySource(locations = {"classpath:application-test.properties"})
})
public class TodoRepositoryTest {

    @Autowired
    TodoRepository todoRepository;

    @DisplayName("{good id} を SELECT")
    @Test
    public void found() {

        assertFalse(todoRepository.findById(1001L).isEmpty());
    }

    @DisplayName("{bad id} を SELECT")
    @Test
    public void notfound() {

        assertTrue(todoRepository.findById(1374L).isEmpty());
    }
}
