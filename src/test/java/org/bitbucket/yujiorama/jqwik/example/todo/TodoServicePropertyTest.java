package org.bitbucket.yujiorama.jqwik.example.todo;

import net.jqwik.api.*;
import net.jqwik.api.Tuple.Tuple2;
import net.jqwik.spring.JqwikSpringSupport;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@TestPropertySources({
        @TestPropertySource(locations = {"classpath:application-test.properties"})
})
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:data-test-service.sql"})
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@JqwikSpringSupport
public class TodoServicePropertyTest {

    @Autowired
    TodoService todoService;

    @Property(tries = 10)
    @Report(Reporting.GENERATED)
    @Label("{good id} を取得(プロパティをテスト)")
    void thereMustExists(@ForAll("goodIds") Long goodId) {

        var actual = todoService.findById(goodId);

        assertFalse(actual.isEmpty());
        assertEquals(goodId, actual.get().getId());
    }

    @Provide
    Arbitrary<Long> goodIds() {
        return Arbitraries.longs().between(1L, 999L);
    }

    @Example
    @Label("{1000} を取得(具体例をテスト)")
    void todo1000MustExist() {

        var goodId = 1000L;

        var actual = todoService.findById(goodId);

        assertFalse(actual.isEmpty());
        assertEquals(goodId, actual.get().getId());
    }

    @Group
    @Label("{bad id} グループ")
    class OnBadId {

        @Disabled("XXX jqwik-spring が @Group に未対応")
        @Property(tries = 10)
        @Report(Reporting.GENERATED)
        @Label("{bad id} を取得(プロパティをテスト)")
        void thereMustNotExists(@ForAll("badIds") Long badId) {

            var actual = todoService.findById(badId);

            assertTrue(actual.isEmpty());
        }

        @Provide
        Arbitrary<Long> badIds() {
            return Arbitraries.longs().greaterOrEqual(1001L).lessOrEqual(Long.MAX_VALUE);
        }
    }

    @Property(tries = 10)
    @FromData("todoIds")
    @Report(Reporting.GENERATED)
    @Label("{bad id} を取得(データ駆動テスト)")
    void dataDriven(@ForAll Long todoId, @ForAll Boolean expected) {

        var actual = todoService.findById(todoId);

        assertEquals(expected, actual.isPresent());
    }

    @Data
    Iterable<Tuple2<Long, Boolean>> todoIds() {

        return Table.of(
                Tuple.of(111L, true),
                Tuple.of(2001L, false),
                Tuple.of(3001L, false),
                Tuple.of(4001L, false),
                Tuple.of(5001L, false)
        );
    }

    @Property(tries = 10)
    @Report(Reporting.GENERATED)
    @Label("example を登録(プロパティをテスト)")
    void saveOk(@ForAll("examples") TodoCreationRequest example) {

        var actual = todoService.save(example);

        assertNotNull(actual.getId());
        assertEquals(example.getTitle(), actual.getTitle());
        assertEquals(example.getNote(), actual.getNote());
    }

    @Provide
    Arbitrary<TodoCreationRequest> examples() {
        var titles = Arbitraries.strings()//
                .all()//
                .ofMinLength(1)//
                .ofMaxLength(100);
        var notes = Arbitraries.strings()//
                .all()//
                .ofMinLength(1)//
                .ofMaxLength(100)//
                .injectNull(0.01);

        return Combinators.combine(titles, notes)
                .as((title, note) -> TodoCreationRequest.of(title).withNote(note));
    }
}
