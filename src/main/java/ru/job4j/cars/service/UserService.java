package ru.job4j.cars.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.common.model.user.User;
import ru.job4j.cars.repository.user.UserRepository;

import java.util.Optional;

/**
 * Сервис для обработки пользователей
 */
@ThreadSafe
@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Добавление нового пользователя
     * @param user - пользователь
     * @return возвращает пользователя
     */
    public User create(User user) {
        return repository.create(user);
    }

    /**
     * Поиск пользователя
     * @param user - пользователь
     * @return возвращает пользователя
     */
    public Optional<User> findByLogin(User user) {
        return repository.findByLogin(user.getLogin());
    }

}
