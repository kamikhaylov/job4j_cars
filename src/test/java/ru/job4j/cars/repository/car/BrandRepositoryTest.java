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
import ru.job4j.cars.common.model.car.Brand;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static ru.job4j.cars.repository.CreatedDtoUtils.createBrand;

class BrandRepositoryTest {

    private final StandardServiceRegistry registry =
            new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf =
            new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final BrandRepository brandRepository = new BrandRepository(crudRepository);

    private Brand brand;

    @BeforeEach
    public void before() {
        brand = createBrand(brandRepository);
    }

    @AfterEach
    public void after() {
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createQuery("delete from Brand").executeUpdate();
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
        Brand brandToUpdate = brandRepository.findById(brand.getId()).get();
        brandToUpdate.setName("new");
        brandRepository.update(brandToUpdate);
        Brand result = brandRepository.findById(brand.getId()).get();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(brandToUpdate.getName(), result.getName());
    }

    @Test
    public void whenDelete() {
        brandRepository.delete(brand.getId());
        Optional<Brand> result = brandRepository.findById(brand.getId());

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void whenFindAll() {
        List<Brand> result = brandRepository.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.size() > 0);
    }

    @Test
    public void whenFindById() {
        Brand result = brandRepository.findById(brand.getId()).get();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(brand.getName(), result.getName());
    }
}