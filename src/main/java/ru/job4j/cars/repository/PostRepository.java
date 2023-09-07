package ru.job4j.cars.repository;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.common.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Методы для работы с таблицей auto_post.
 */
@Repository
@AllArgsConstructor
public class PostRepository {

    private final CrudRepository crudRepository;

    /**
     * Сохранить объявление в базе.
     *
     * @param post объявление.
     * @return объявление с id.
     */
    public Post create(Post post) {
        crudRepository.run(session -> session.persist(post));
        return post;
    }

    /**
     * Обновить объявление в базе.
     *
     * @param post объявление.
     */
    public void update(Post post) {
        crudRepository.run(session -> session.update(post));
    }

    /**
     * Удалить объявление по id.
     *
     * @param id ID объявление
     */
    public void delete(int id) {
        crudRepository.run(
                "delete from Post where id = :fId",
                Map.of("fId", id)
        );
    }

    /**
     * Список объявлений по id.
     *
     * @return список объявлений.
     */
    public List<Post> findAll() {
        return crudRepository.query(
                "from Post order by id asc",
                Post.class);
    }

    /**
     * Найти объявление по ID
     *
     * @param id ID
     * @return объявление.
     */
    public Optional<Post> findById(int id) {
        return crudRepository.optional(
                "from Post where id = :fId", Post.class,
                Map.of("fId", id)
        );
    }

    /**
     * Получить объявления за последний день.
     *
     * @return объявления.
     */
    public List<Post> findAllPostAtLastDay() {
        return crudRepository.query(
                "from Post where created >= :fDate",
                Post.class,
                Map.of("fDate", LocalDateTime.now().minusDays(1)));
    }

    /**
     * Получить объявления с фото.
     *
     * @return объявления.
     */
    public List<Post> findAllPostWithPhoto() {
        return crudRepository.query(
                "from Post where photo_id is not null",
                Post.class);
    }

    /**
     * Получить объявления определенной марки.
     *
     * @return объявления.
     */
    public List<Post> findAllPostWithModel(String name) {
        return crudRepository.query(
                "from Post p join fetch p.car where p.car.name = :fName",
                Post.class,
                Map.of("fName", name));
    }
}
