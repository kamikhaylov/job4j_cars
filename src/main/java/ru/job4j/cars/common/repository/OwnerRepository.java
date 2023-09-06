package ru.job4j.cars.common.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.common.model.Owner;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Методы для работы с таблицей owners.
 */
@Repository
@AllArgsConstructor
public class OwnerRepository {

    private final CrudRepository crudRepository;

    /**
     * Сохранить владельца в базе.
     *
     * @param owner владелец.
     * @return владелец с id.
     */
    public Owner create(Owner owner) {
        crudRepository.run(session -> session.persist(owner));
        return owner;
    }

    /**
     * Обновить владелеа в базе.
     *
     * @param owner владелец.
     */
    public void update(Owner owner) {
        crudRepository.run(session -> session.merge(owner));
    }

    /**
     * Удалить владелеца по id.
     *
     * @param id ID владелец
     */
    public void delete(int id) {
        crudRepository.run(
                "delete from Owner where id = :fId",
                Map.of("fId", id)
        );
    }

    /**
     * Список владельцев отсортированных по id.
     *
     * @return список владельцев.
     */
    public List<Owner> findAll() {
        return crudRepository.query(
                "from Owner order by id asc",
                Owner.class);
    }

    /**
     * Найти владельца по ID
     *
     * @param id ID
     * @return владелец.
     */
    public Optional<Owner> findById(int id) {
        return crudRepository.optional(
                "from Owner where id = :fId", Owner.class,
                Map.of("fId", id)
        );
    }

}
