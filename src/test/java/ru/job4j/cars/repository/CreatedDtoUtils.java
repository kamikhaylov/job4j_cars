package ru.job4j.cars.repository;

import ru.job4j.cars.common.model.car.Brand;
import ru.job4j.cars.common.model.car.Car;
import ru.job4j.cars.common.model.car.Color;
import ru.job4j.cars.common.model.car.Engine;
import ru.job4j.cars.common.model.post.Category;
import ru.job4j.cars.common.model.car.History;
import ru.job4j.cars.common.model.car.Owner;
import ru.job4j.cars.common.model.post.Photo;
import ru.job4j.cars.common.model.post.Post;
import ru.job4j.cars.common.model.user.User;
import ru.job4j.cars.repository.car.BrandRepository;
import ru.job4j.cars.repository.car.CarRepository;
import ru.job4j.cars.repository.car.ColorRepository;
import ru.job4j.cars.repository.car.EngineRepository;
import ru.job4j.cars.repository.post.CategoryRepository;
import ru.job4j.cars.repository.car.HistoryRepository;
import ru.job4j.cars.repository.car.OwnerRepository;
import ru.job4j.cars.repository.post.PhotoRepository;
import ru.job4j.cars.repository.post.PostRepository;
import ru.job4j.cars.repository.user.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class CreatedDtoUtils {

    private CreatedDtoUtils() {
    }

    public static Engine createEngine(EngineRepository engineRepository) {
        Engine engine = new Engine();
        engine.setName("test_engine");
        return engineRepository.create(engine);
    }

    public static Category createCategory(CategoryRepository categoryRepository) {
        Category category = new Category();
        category.setName("test_category");
        return categoryRepository.create(category);
    }

    public static Color createColor(ColorRepository colorRepository) {
        Color color = new Color();
        color.setName("test_color");
        return colorRepository.create(color);
    }

    public static Brand createBrand(BrandRepository brandRepository) {
        Brand brand = new Brand();
        brand.setName("test_name");
        brand.setModel("test_model");
        return brandRepository.create(brand);
    }

    public static Car createCar(CarRepository carRepository, Engine engine,
                                Brand brand, Color color) {
        Car car = new Car();
        car.setBrand(brand);
        car.setEngine(engine);
        car.setColor(color);
        car.setYear(2022);
        car.setMileage(12000);
        car.setVin("test_number_vin");
        return carRepository.create(car);
    }

    public static User createUser(UserRepository userRepository) {
        User user = new User();
        user.setLogin("test_login");
        user.setPassword("test_password");
        user.setName("test_name");
        return userRepository.create(user);
    }

    public static Owner createOwner(OwnerRepository ownerRepository, User user) {
        Owner owner = new Owner();
        owner.setName(user.getLogin());
        owner.setUser(user);
        return ownerRepository.create(owner);
    }

    public static Photo createPhoto(PhotoRepository photoRepository) {
        Photo photo = new Photo();
        photo.setName("test_photo_name");
        photo.setPath("test_path_name");
        return photoRepository.create(photo);
    }

    public static Post createPost(PostRepository postRepository, User user, Car car,
                                  Photo photo, Category category) {
        Post post = new Post();
        post.setCreated(LocalDateTime.now());
        post.setText("test_text_post");
        post.setUser(user);
        post.setCar(car);
        post.setPhoto(photo);
        post.setCategory(category);
        post.setIsSold(false);
        post.setPrice(BigDecimal.TEN);
        return postRepository.create(post);
    }

    public static History createHistory(HistoryRepository historyRepository, Owner owner) {
        History history = new History();
        history.setStartAt(LocalDateTime.now().minusDays(1));
        history.setEndAt(LocalDateTime.now());
        history.setOwner(owner);
        return historyRepository.create(history);
    }
}
