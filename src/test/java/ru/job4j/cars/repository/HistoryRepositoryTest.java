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
import ru.job4j.cars.common.model.car.History;
import ru.job4j.cars.repository.car.HistoryRepository;
import ru.job4j.cars.repository.car.OwnerRepository;
import ru.job4j.cars.repository.user.UserRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static ru.job4j.cars.repository.CreatedDtoUtils.createHistory;
import static ru.job4j.cars.repository.CreatedDtoUtils.createOwner;
import static ru.job4j.cars.repository.CreatedDtoUtils.createUser;

class HistoryRepositoryTest {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final StandardServiceRegistry registry =
            new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf =
            new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final UserRepository userRepository = new UserRepository(crudRepository);
    private final OwnerRepository ownerRepository = new OwnerRepository(crudRepository);
    private final HistoryRepository historyRepository = new HistoryRepository(crudRepository);

    private History history;

    @BeforeEach
    public void before() {
        history = createHistory(historyRepository,
                createOwner(ownerRepository, createUser(userRepository)));
    }

    @AfterEach
    public void after() {
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createQuery("delete from History").executeUpdate();
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
        History historyToUpdate = historyRepository.findById(history.getId()).get();
        historyToUpdate.setEndAt(LocalDateTime.now());
        historyRepository.update(historyToUpdate);
        History result = historyRepository.findById(history.getId()).get();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(historyToUpdate.getId(), result.getId());
        Assertions.assertEquals(historyToUpdate.getEndAt().format(FORMATTER),
                result.getEndAt().format(FORMATTER));
    }

    @Test
    public void whenDelete() {
        historyRepository.delete(history.getId());
        Optional<History> result = historyRepository.findById(history.getId());

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void whenFindAll() {
        List<History> result = historyRepository.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(history.getId(), result.get(0).getId());
    }

    @Test
    public void whenFindById() {
        History result = historyRepository.findById(history.getId()).get();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(history.getId(), result.getId());
    }
}