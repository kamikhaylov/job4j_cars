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
import ru.job4j.cars.common.model.Owner;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static ru.job4j.cars.common.repository.CreatedDtoUtils.createOwner;
import static ru.job4j.cars.common.repository.CreatedDtoUtils.createUser;

class OwnerRepositoryTest {

    private final StandardServiceRegistry registry =
            new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf =
            new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final UserRepository userRepository = new UserRepository(crudRepository);
    private final OwnerRepository ownerRepository = new OwnerRepository(crudRepository);

    private Owner owner;

    @BeforeEach
    public void before() {
        owner = createOwner(ownerRepository, createUser(userRepository));
    }

    @AfterEach
    public void after() {
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createQuery("delete from Owner").executeUpdate();
            session.createQuery("delete from User").executeUpdate();
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
        Owner ownerToUpdate = ownerRepository.findById(owner.getId()).get();
        ownerToUpdate.setName("new");
        ownerRepository.update(ownerToUpdate);
        Owner result = ownerRepository.findById(owner.getId()).get();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(ownerToUpdate.getName(), result.getName());
    }

    @Test
    public void whenDelete() {
        ownerRepository.delete(owner.getId());
        Optional<Owner> result = ownerRepository.findById(owner.getId());

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void whenFindAll() {
        List<Owner> result = ownerRepository.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(owner.getName(), result.get(0).getName());
    }

    @Test
    public void whenFindById() {
        Owner result = ownerRepository.findById(owner.getId()).get();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(owner.getName(), result.getName());
    }
}