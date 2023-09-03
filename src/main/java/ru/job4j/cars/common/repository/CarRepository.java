package ru.job4j.cars.common.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.common.model.Car;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Методы для работы с таблицей car.
 */
@Repository
@AllArgsConstructor
public class CarRepository {

    private final CrudRepository crudRepository;

    /**
     * Сохранить автомобиль в базе.
     *
     * @param car автомобиль.
     * @return автомобиль с id.
     */
    public Car create(Car car) {
        crudRepository.run(session -> session.persist(car));
        return car;
    }

    /**
     * Обновить автомобиль в базе.
     *
     * @param car автомобиль.
     */
    public void update(Car car) {
        crudRepository.run(session -> session.merge(car));
    }

    /**
     * Удалить автомобиль по id.
     *
     * @param id ID автомобиля
     */
    public void delete(int id) {
        crudRepository.run(
                "delete from Car where id = :fId",
                Map.of("fId", id)
        );
    }

    /**
     * Список автомобилей отсортированных по id.
     *
     * @return список автомобилей.
     */
    public List<Car> findAll() {
        return crudRepository.query(
                "from Car order by id asc",
                Car.class);
    }

    /**
     * Найти автомобиль по ID
     *
     * @param id ID
     * @return автомобиль.
     */
    public Optional<Car> findById(int id) {
        return crudRepository.optional(
                "from Car where id = :fId", Car.class,
                Map.of("fId", id)
        );
    }

}
