package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.common.model.car.Engine;
import ru.job4j.cars.repository.car.EngineRepository;

import java.util.List;

/**
 * Сервис для двигателей
 */
@ThreadSafe
@Service
@AllArgsConstructor
public class EngineService {

    private final EngineRepository repository;

    public List<Engine> getAll() {
        return repository.findAll();
    }
}
