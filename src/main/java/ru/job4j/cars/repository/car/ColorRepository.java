package ru.job4j.cars.repository.car;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.common.model.car.Color;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Методы для работы с таблицей color.
 */
@Repository
@AllArgsConstructor
public class ColorRepository {

    private final CrudRepository crudRepository;

    /**
     * Сохранить цвет в базе.
     *
     * @param color цвет.
     * @return цвет с id.
     */
    public Color create(Color color) {
        crudRepository.run(session -> session.persist(color));
        return color;
    }

    /**
     * Обновить цвет в базе.
     *
     * @param color цвет.
     */
    public void update(Color color) {
        crudRepository.run(session -> session.merge(color));
    }

    /**
     * Удалить цвет по id.
     *
     * @param id ID цвет
     */
    public void delete(int id) {
        crudRepository.run(
                "delete from Color where id = :fId",
                Map.of("fId", id)
        );
    }

    /**
     * Список цветов отсортированных по id.
     *
     * @return список цвет.
     */
    public List<Color> findAll() {
        return crudRepository.query(
                "from Color order by id asc",
                Color.class);
    }

    /**
     * Найти цвет по ID
     *
     * @param id ID
     * @return цвет.
     */
    public Optional<Color> findById(int id) {
        return crudRepository.optional(
                "from Color where id = :fId", Color.class,
                Map.of("fId", id)
        );
    }

}
