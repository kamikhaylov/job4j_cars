package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.common.model.car.Brand;
import ru.job4j.cars.repository.car.BrandRepository;

import java.util.List;

/**
 * Сервис для марок
 */
@ThreadSafe
@Service
@AllArgsConstructor
public class BrandService {

    private final BrandRepository repository;

    public List<Brand> getAll() {
        return repository.findAll();
    }
}
