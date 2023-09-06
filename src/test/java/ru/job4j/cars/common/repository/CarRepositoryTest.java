package ru.job4j.cars.common.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.common.model.Car;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static ru.job4j.cars.common.repository.CreatedDtoUtils.createCar;
import static ru.job4j.cars.common.repository.CreatedDtoUtils.createEngine;

class CarRepositoryTest {

    private final StandardServiceRegistry registry =
            new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf =
            new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final CarRepository carRepository = new CarRepository(crudRepository);
    private final EngineRepository engineRepository = new EngineRepository(crudRepository);

    private Car car;

    @BeforeEach
    public void before() {
        car = createCar(carRepository, createEngine(engineRepository));
    }

    @AfterEach
    public void after() {
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createQuery("delete from Car").executeUpdate();
            session.createQuery("delete from Engine").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (nonNull(transaction)) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    @Test
    public void whenUpdate() {
        Car carToUpdate = carRepository.findById(car.getId()).get();
        carToUpdate.setName("new");
        carRepository.update(carToUpdate);
        Car result = carRepository.findById(car.getId()).get();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(carToUpdate.getName(), result.getName());
    }

    @Test
    public void whenDelete() {
        carRepository.delete(car.getId());
        Optional<Car> result = carRepository.findById(car.getId());

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void whenFindAll() {
        List<Car> result = carRepository.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(car.getName(), result.get(0).getName());
    }

    @Test
    public void whenFindById() {
        Car result = carRepository.findById(car.getId()).get();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(car.getName(), result.getName());
    }
}