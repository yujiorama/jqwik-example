package org.bitbucket.yujiorama.jqwik.example.todo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@TestPropertySources({
        @TestPropertySource(locations = {"classpath:application-test.properties"})
})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:data-test-service.sql"})
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TodoServiceTest {

    @Autowired
    TodoService todoService;

    @Order(1)
    @DisplayName("{good id} を取得")
    @ParameterizedTest
    @ValueSource(
            longs = {
                    1L,
                    12L,
                    23L,
                    1000L,
            }
    )
    public void found(Long goodId) {

        var actual = todoService.findById(goodId);

        assertFalse(actual.isEmpty());
        assertEquals(goodId, actual.get().getId());
    }

    @Order(2)
    @DisplayName("{bad id} を取得")
    @ParameterizedTest
    @ValueSource(
            longs = {
                    2001L,
                    3001L,
                    4001L,
                    5001L,
            }
    )
    public void notfound(Long badId) {

        var actual = todoService.findById(badId);

        assertTrue(actual.isEmpty());
    }

    @Order(3)
    @DisplayName("example を登録")
    @ParameterizedTest
    @MethodSource({"creationRequestExampleProvider"})
    public void saved(TodoCreationRequest example) {

        var actual = todoService.save(example);

        assertNotNull(actual.getId());
        assertEquals(example.getTitle(), actual.getTitle());
        assertEquals(example.getNote(), actual.getNote());
    }

    private static Stream<TodoCreationRequest> creationRequestExampleProvider() {

        return Stream.of(
                new String[]{"first example", "note"},
                new String[]{"null note example", null},
                new String[]{"empty note example", ""},
                new String[]{"日本語タイトル example", "note"},
                new String[]{"japanese note example", "日本語メモ"}
        ).map(ar ->
                TodoCreationRequest.of(ar[0]/*title*/).withNote(ar[1]/*note*/));
    }
}
