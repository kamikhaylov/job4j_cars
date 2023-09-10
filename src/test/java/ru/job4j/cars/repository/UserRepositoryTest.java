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
import ru.job4j.cars.common.model.user.User;
import ru.job4j.cars.repository.user.UserRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static ru.job4j.cars.repository.CreatedDtoUtils.createUser;

class UserRepositoryTest {

    private final StandardServiceRegistry registry =
            new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf =
            new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final UserRepository userRepository = new UserRepository(crudRepository);

    private User user;

    @BeforeEach
    public void before() {
        user = createUser(userRepository);
    }

    @AfterEach
    public void after() {
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
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
        User userToUpdate = userRepository.findById(user.getId()).get();
        userToUpdate.setPassword("new");
        userRepository.update(userToUpdate);
        User result = userRepository.findById(user.getId()).get();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(userToUpdate.getPassword(), result.getPassword());
    }

    @Test
    public void whenDelete() {
        userRepository.delete(user.getId());
        Optional<User> result = userRepository.findById(user.getId());

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void whenFindAll() {
        List<User> result = userRepository.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(user.getLogin(), result.get(0).getLogin());
    }

    @Test
    public void whenFindById() {
        User result = userRepository.findById(user.getId()).get();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(user.getLogin(), result.getLogin());
    }

    @Test
    public void whenFindByLikeLogin() {
        List<User> result = userRepository.findByLikeLogin(user.getLogin().substring(1));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(user.getLogin(), result.get(0).getLogin());
    }

    @Test
    public void whenFindByLogin() {
        User result = userRepository.findByLogin(user.getLogin()).get();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(user.getLogin(), result.getLogin());
    }
}