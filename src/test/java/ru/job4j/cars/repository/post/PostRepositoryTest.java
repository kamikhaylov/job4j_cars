package ru.job4j.cars.repository.post;

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
import ru.job4j.cars.common.model.post.Post;
import ru.job4j.cars.repository.CrudRepository;
import ru.job4j.cars.repository.car.BrandRepository;
import ru.job4j.cars.repository.car.CarRepository;
import ru.job4j.cars.repository.car.ColorRepository;
import ru.job4j.cars.repository.car.EngineRepository;
import ru.job4j.cars.repository.post.CategoryRepository;
import ru.job4j.cars.repository.post.PhotoRepository;
import ru.job4j.cars.repository.post.PostRepository;
import ru.job4j.cars.repository.user.UserRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static ru.job4j.cars.repository.CreatedDtoUtils.createBrand;
import static ru.job4j.cars.repository.CreatedDtoUtils.createCar;
import static ru.job4j.cars.repository.CreatedDtoUtils.createCategory;
import static ru.job4j.cars.repository.CreatedDtoUtils.createColor;
import static ru.job4j.cars.repository.CreatedDtoUtils.createEngine;
import static ru.job4j.cars.repository.CreatedDtoUtils.createPhoto;
import static ru.job4j.cars.repository.CreatedDtoUtils.createPost;
import static ru.job4j.cars.repository.CreatedDtoUtils.createUser;

class PostRepositoryTest {
    private final StandardServiceRegistry registry =
            new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf =
            new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final CarRepository carRepository = new CarRepository(crudRepository);
    private final EngineRepository engineRepository = new EngineRepository(crudRepository);
    private final UserRepository userRepository = new UserRepository(crudRepository);
    private final PostRepository postRepository = new PostRepository(crudRepository);
    private final PhotoRepository photoRepository = new PhotoRepository(crudRepository, null);
    private final BrandRepository brandRepository = new BrandRepository(crudRepository);
    private final ColorRepository colorRepository = new ColorRepository(crudRepository);
    private final CategoryRepository categoryRepository = new CategoryRepository(crudRepository);

    private Post post;

    @BeforeEach
    public void before() {
        post = createPost(postRepository,
                createUser(userRepository),
                createCar(carRepository,
                        createEngine(engineRepository),
                        createBrand(brandRepository),
                        createColor(colorRepository)),
                createPhoto(photoRepository),
                createCategory(categoryRepository));
    }

    @AfterEach
    public void after() {
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createQuery("delete from Post").executeUpdate();
            session.createQuery("delete from Photo").executeUpdate();
            session.createQuery("delete from Category").executeUpdate();
            session.createQuery("delete from Car").executeUpdate();
            session.createQuery("delete from Engine").executeUpdate();
            session.createQuery("delete from Brand").executeUpdate();
            session.createQuery("delete from Color").executeUpdate();
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
        Post postToUpdate = postRepository.findById(post.getId()).get();
        postToUpdate.setText("new");
        postRepository.update(postToUpdate);
        Post result = postRepository.findById(post.getId()).get();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(postToUpdate.getText(), result.getText());
    }

    @Test
    public void whenDelete() {
        postRepository.delete(post.getId());
        Optional<Post> result = postRepository.findById(post.getId());

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void whenFindAll() {
        List<Post> result = postRepository.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(post.getText(), result.get(0).getText());
    }

    @Test
    public void whenFindById() {
        Post result = postRepository.findById(post.getId()).get();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(post.getText(), result.getText());
    }

    @Test
    public void whenFindAllPostAtLastDay() {
        List<Post> result = postRepository.findAllPostAtLastDay();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(post.getText(), result.get(0).getText());
    }

    @Test
    public void whenFindAllPostWithPhoto() {
        List<Post> result = postRepository.findAllPostWithPhoto();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(post.getText(), result.get(0).getText());
    }

    @Test
    public void whenFindAllPostWithModel() {
        List<Post> result =
                postRepository.findAllPostWithModel(post.getCar().getBrand().getModel());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(post.getText(), result.get(0).getText());
        Assertions.assertEquals(post.getCar().getBrand().getModel(),
                result.get(0).getCar().getBrand().getModel());
    }
}