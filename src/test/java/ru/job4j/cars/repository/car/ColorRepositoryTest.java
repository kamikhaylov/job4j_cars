package ru.job4j.cars.repository.car;

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
import ru.job4j.cars.common.model.car.Color;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static ru.job4j.cars.repository.CreatedDtoUtils.createColor;

class ColorRepositoryTest {

    private final StandardServiceRegistry registry =
            new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf =
            new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final ColorRepository colorRepository = new ColorRepository(crudRepository);

    private Color color;

    @BeforeEach
    public void before() {
        color = createColor(colorRepository);
    }

    @AfterEach
    public void after() {
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
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
        Color colorToUpdate = colorRepository.findById(color.getId()).get();
        colorToUpdate.setName("new");
        colorRepository.update(colorToUpdate);
        Color result = colorRepository.findById(color.getId()).get();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(colorToUpdate.getName(), result.getName());
    }

    @Test
    public void whenDelete() {
        colorRepository.delete(color.getId());
        Optional<Color> result = colorRepository.findById(color.getId());

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void whenFindAll() {
        List<Color> result = colorRepository.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(color.getName(), result.get(0).getName());
    }

    @Test
    public void whenFindById() {
        Color result = colorRepository.findById(color.getId()).get();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(color.getName(), result.getName());
    }
}