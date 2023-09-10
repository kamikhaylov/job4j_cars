package ru.job4j.cars.repository.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.common.model.post.Category;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Методы для работы с таблицей post_category.
 */
@Repository
@AllArgsConstructor
public class CategoryRepository {

    private final CrudRepository crudRepository;

    /**
     * Сохранить категорию в базе.
     *
     * @param category категория.
     * @return категория с id.
     */
    public Category create(Category category) {
        crudRepository.run(session -> session.persist(category));
        return category;
    }

    /**
     * Обновить категорию в базе.
     *
     * @param category категория.
     */
    public void update(Category category) {
        crudRepository.run(session -> session.merge(category));
    }

    /**
     * Удалить категорию по id.
     *
     * @param id ID категории
     */
    public void delete(int id) {
        crudRepository.run(
                "delete from Category where id = :fId",
                Map.of("fId", id)
        );
    }

    /**
     * Список категориий отсортированных по id.
     *
     * @return список категорий.
     */
    public List<Category> findAll() {
        return crudRepository.query(
                "from Category order by id asc",
                Category.class);
    }

    /**
     * Найти категрию по ID
     *
     * @param id ID
     * @return категория.
     */
    public Optional<Category> findById(int id) {
        return crudRepository.optional(
                "from Category where id = :fId", Category.class,
                Map.of("fId", id)
        );
    }

}
