package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.common.model.car.Brand;
import ru.job4j.cars.common.model.car.Color;
import ru.job4j.cars.repository.car.BrandRepository;
import ru.job4j.cars.repository.car.ColorRepository;

import java.util.List;

/**
 * Сервис для цветов
 */
@ThreadSafe
@Service
@AllArgsConstructor
public class ColorService {

    private final ColorRepository repository;

    public List<Color> getAll() {
        return repository.findAll();
    }
}
