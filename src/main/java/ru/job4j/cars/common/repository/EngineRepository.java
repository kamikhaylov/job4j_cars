package ru.job4j.cars.common.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.common.model.Engine;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Методы для работы с таблицей engine.
 */
@Repository
@AllArgsConstructor
public class EngineRepository {

    private final CrudRepository crudRepository;

    /**
     * Сохранить дигатель в базе.
     *
     * @param engine двигатель.
     * @return дигатель с id.
     */
    public Engine create(Engine engine) {
        crudRepository.run(session -> session.persist(engine));
        return engine;
    }

    /**
     * Обновить двигатель в базе.
     *
     * @param engine дигатель.
     */
    public void update(Engine engine) {
        crudRepository.run(session -> session.merge(engine));
    }

    /**
     * Удалить дигатель по id.
     *
     * @param id ID дигатель
     */
    public void delete(int id) {
        crudRepository.run(
                "delete from Engine where id = :fId",
                Map.of("fId", id)
        );
    }

    /**
     * Список двигателей отсортированных по id.
     *
     * @return список двигателей.
     */
    public List<Engine> findAll() {
        return crudRepository.query(
                "from Engine order by id asc",
                Engine.class);
    }

    /**
     * Найти двигатель по ID
     *
     * @param id ID
     * @return двигатель.
     */
    public Optional<Engine> findById(int id) {
        return crudRepository.optional(
                "from Engine where id = :fId", Engine.class,
                Map.of("fId", id)
        );
    }

}
