package ru.job4j.cars.repository.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.common.model.post.Photo;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Методы для работы с таблицей photo.
 */
@Repository
@AllArgsConstructor
public class PhotoRepository {

    private final CrudRepository crudRepository;

    /**
     * Сохранить фото автомобиля в базе.
     *
     * @param photo фото.
     * @return фото с id.
     */
    public Photo create(Photo photo) {
        crudRepository.run(session -> session.persist(photo));
        return photo;
    }

    /**
     * Обновить фото автомобиля в базе.
     *
     * @param photo фото.
     */
    public void update(Photo photo) {
        crudRepository.run(session -> session.merge(photo));
    }

    /**
     * Удалить фото автомобиля по id.
     *
     * @param id ID фото
     */
    public void delete(int id) {
        crudRepository.run(
                "delete from Photo where id = :fId",
                Map.of("fId", id)
        );
    }

    /**
     * Список фото отсортированных по id.
     *
     * @return список фото.
     */
    public List<Photo> findAll() {
        return crudRepository.query(
                "from Photo order by id asc",
                Photo.class);
    }

    /**
     * Найти фото по ID
     *
     * @param id ID
     * @return фото.
     */
    public Optional<Photo> findById(int id) {
        return crudRepository.optional(
                "from Photo where id = :fId", Photo.class,
                Map.of("fId", id)
        );
    }

}
