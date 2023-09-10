package ru.job4j.cars.repository.car;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.common.model.car.History;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Методы для работы с таблицей history.
 */
@Repository
@AllArgsConstructor
public class HistoryRepository {

    private final CrudRepository crudRepository;

    /**
     * Сохранить историю в базе.
     *
     * @param history история.
     * @return автомобиль с id.
     */
    public History create(History history) {
        crudRepository.run(session -> session.persist(history));
        return history;
    }

    /**
     * Обновить историю в базе.
     *
     * @param history история.
     */
    public void update(History history) {
        crudRepository.run(session -> session.merge(history));
    }

    /**
     * Удалить историю по id.
     *
     * @param id ID история
     */
    public void delete(int id) {
        crudRepository.run(
                "delete from History where id = :fId",
                Map.of("fId", id)
        );
    }

    /**
     * Список историй отсортированных по id.
     *
     * @return список историй.
     */
    public List<History> findAll() {
        return crudRepository.query(
                "from History order by id asc",
                History.class);
    }

    /**
     * Найти историю по ID
     *
     * @param id ID
     * @return история.
     */
    public Optional<History> findById(int id) {
        return crudRepository.optional(
                "from History where id = :fId", History.class,
                Map.of("fId", id)
        );
    }

}
