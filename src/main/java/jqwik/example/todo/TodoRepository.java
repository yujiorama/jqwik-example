package jqwik.example.todo;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TodoRepository extends CrudRepository<Todo, Long> {

    @Query( //
            name = "findById", //
            value = "SELECT * FROM TODO WHERE ID=:id")
    Optional<Todo> findById(@Param("id") Long id);
}
