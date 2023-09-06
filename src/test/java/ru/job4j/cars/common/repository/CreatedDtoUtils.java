package ru.job4j.cars.common.repository;

import ru.job4j.cars.common.model.Car;
import ru.job4j.cars.common.model.Engine;
import ru.job4j.cars.common.model.History;
import ru.job4j.cars.common.model.Owner;
import ru.job4j.cars.common.model.Photo;
import ru.job4j.cars.common.model.Post;
import ru.job4j.cars.common.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class CreatedDtoUtils {

    public static Engine createEngine(EngineRepository engineRepository) {
        Engine engine = new Engine();
        engine.setName("test_engine");
        return engineRepository.create(engine);
    }

    public static Car createCar(CarRepository carRepository, Engine engine) {
        Car car = new Car();
        car.setName("test_car_name");
        car.setEngine(engine);
        return carRepository.create(car);
    }

    public static User createUser(UserRepository userRepository) {
        User user = new User();
        user.setLogin("test_login");
        user.setPassword("test_password");
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
                            Photo photo) {
        Post post = new Post();
        post.setCreated(LocalDateTime.now());
        post.setText("test_text_post");
        post.setUser(user);
        post.setCar(car);
        post.setPhoto(photo);
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
