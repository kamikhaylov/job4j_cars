package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.common.model.post.Category;
import ru.job4j.cars.repository.post.CategoryRepository;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для категорий
 */
@ThreadSafe
@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> getAll() {
        return repository.findAll();
    }

    public Category getById(Integer id) {
        return repository.findById(id).get();
    }
}
