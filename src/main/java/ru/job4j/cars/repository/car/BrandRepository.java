package ru.job4j.cars.repository.car;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.common.model.car.Brand;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Методы для работы с таблицей car_brand.
 */
@Repository
@AllArgsConstructor
public class BrandRepository {

    private final CrudRepository crudRepository;

    /**
     * Сохранить марку в базе.
     *
     * @param brand марка.
     * @return марка с id.
     */
    public Brand create(Brand brand) {
        crudRepository.run(session -> session.persist(brand));
        return brand;
    }

    /**
     * Обновить марку в базе.
     *
     * @param brand марка.
     */
    public void update(Brand brand) {
        crudRepository.run(session -> session.merge(brand));
    }

    /**
     * Удалить марку по id.
     *
     * @param id ID марка
     */
    public void delete(int id) {
        crudRepository.run(
                "delete from Brand where id = :fId",
                Map.of("fId", id)
        );
    }

    /**
     * Список марок отсортированных по id.
     *
     * @return список марок.
     */
    public List<Brand> findAll() {
        return crudRepository.query(
                "from Brand order by id asc",
                Brand.class);
    }

    /**
     * Найти марку по ID
     *
     * @param id ID
     * @return двигатель.
     */
    public Optional<Brand> findById(int id) {
        return crudRepository.optional(
                "from Brand where id = :fId", Brand.class,
                Map.of("fId", id)
        );
    }

}
