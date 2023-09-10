package ru.job4j.cars.repository;

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
import ru.job4j.cars.common.model.car.Car;
import ru.job4j.cars.repository.car.BrandRepository;
import ru.job4j.cars.repository.car.CarRepository;
import ru.job4j.cars.repository.car.ColorRepository;
import ru.job4j.cars.repository.car.EngineRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static ru.job4j.cars.repository.CreatedDtoUtils.createBrand;
import static ru.job4j.cars.repository.CreatedDtoUtils.createCar;
import static ru.job4j.cars.repository.CreatedDtoUtils.createColor;
import static ru.job4j.cars.repository.CreatedDtoUtils.createEngine;

class CarRepositoryTest {

    private final StandardServiceRegistry registry =
            new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf =
            new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final CarRepository carRepository = new CarRepository(crudRepository);
    private final EngineRepository engineRepository = new EngineRepository(crudRepository);
    private final BrandRepository brandRepository = new BrandRepository(crudRepository);
    private final ColorRepository colorRepository = new ColorRepository(crudRepository);

    private Car car;

    @BeforeEach
    public void before() {
        car = createCar(carRepository,
                createEngine(engineRepository),
                createBrand(brandRepository),
                createColor(colorRepository));
    }

    @AfterEach
    public void after() {
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createQuery("delete from Car").executeUpdate();
            session.createQuery("delete from Engine").executeUpdate();
            session.createQuery("delete from Brand").executeUpdate();
            session.createQuery("delete from Color").executeUpdate();
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
        carToUpdate.setVin("new");
        carRepository.update(carToUpdate);
        Car result = carRepository.findById(car.getId()).get();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(carToUpdate.getVin(), result.getVin());
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
        Assertions.assertEquals(car.getVin(), result.get(0).getVin());
    }

    @Test
    public void whenFindById() {
        Car result = carRepository.findById(car.getId()).get();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(car.getVin(), result.getVin());
    }
}