package ru.job4j.cars.common.repository;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.common.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Методы для работы с таблицей auto_post.
 */
@Repository
@AllArgsConstructor
public class PostRepository {

    private final CrudRepository crudRepository;

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
