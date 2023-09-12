package ru.job4j.cars.repository.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.common.model.post.PriceHistory;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Методы для работы с таблицей price_history.
 */
@Repository
@AllArgsConstructor
public class PriceHistoryRepository {

    private final CrudRepository crudRepository;

    /**
     * Сохранить стоимость в базе.
     *
     * @param priceHistory стоимость.
     * @return стоимость с id.
     */
    public PriceHistory create(PriceHistory priceHistory) {
        crudRepository.run(session -> session.persist(priceHistory));
        return priceHistory;
    }

    /**
     * Обновить стоимость в базе.
     *
     * @param priceHistory стоимость.
     */
    public void update(PriceHistory priceHistory) {
        crudRepository.run(session -> session.merge(priceHistory));
    }

    /**
     * Удалить стоимость по id.
     *
     * @param id ID стоимость
     */
    public void delete(int id) {
        crudRepository.run(
                "delete from PriceHistory where id = :fId",
                Map.of("fId", id)
        );
    }

    /**
     * Список цен отсортированных по id.
     *
     * @return список категорий.
     */
    public List<PriceHistory> findAll() {
        return crudRepository.query(
                "from PriceHistory order by id asc",
                PriceHistory.class);
    }

    /**
     * Найти стоимоость по ID
     *
     * @param id ID
     * @return стоимость.
     */
    public Optional<PriceHistory> findById(int id) {
        return crudRepository.optional(
                "from PriceHistory where id = :fId", PriceHistory.class,
                Map.of("fId", id)
        );
    }
}
