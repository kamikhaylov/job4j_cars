package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.common.model.Post;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ParticipatesRepository {
    private final CrudRepository crudRepository;

    public <T> T create(T model) {
        crudRepository.run(session -> session.persist(model));
        return model;
    }

    public <T> void update(T model) {
        crudRepository.run(session -> session.merge(model));
    }

    public void delete(Integer id) {
        crudRepository.run(
                "delete from Post where id = :fId",
                Map.of("fId", id)
        );
    }

    public <T> List<Post> findAll() {
        return crudRepository.query("from Post", Post.class).stream().toList();
    }

    public Optional<Post> findById(Integer id) {
        return crudRepository.optional("from Post order by id asc", Post.class,
                Map.of("fId", id));
    }
}