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
import ru.job4j.cars.common.model.Photo;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static ru.job4j.cars.common.repository.CreatedDtoUtils.createPhoto;

class PhotoRepositoryTest {
    private final StandardServiceRegistry registry =
            new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf =
            new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final PhotoRepository photoRepository = new PhotoRepository(crudRepository);

    private Photo photo;

    @BeforeEach
    public void before() {
        photo = createPhoto(photoRepository);
    }

    @AfterEach
    public void after() {
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createQuery("delete from Photo").executeUpdate();
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
        Photo photoToUpdate = photoRepository.findById(photo.getId()).get();
        photoToUpdate.setName("new");
        photoRepository.update(photoToUpdate);
        Photo result = photoRepository.findById(photo.getId()).get();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(photoToUpdate.getName(), result.getName());
    }

    @Test
    public void whenDelete() {
        photoRepository.delete(photo.getId());
        Optional<Photo> result = photoRepository.findById(photo.getId());

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void whenFindAll() {
        List<Photo> result = photoRepository.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(photo.getName(), result.get(0).getName());
    }

    @Test
    public void whenFindById() {
        Photo result = photoRepository.findById(photo.getId()).get();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(photo.getName(), result.getName());
    }
}