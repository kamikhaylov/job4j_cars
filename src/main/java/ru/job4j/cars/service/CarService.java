package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.common.model.car.Car;
import ru.job4j.cars.repository.car.CarRepository;

/**
 * Сервис для авто
 */
@ThreadSafe
@Service
@AllArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public Car create(Car car) {
        return carRepository.create(car);
    }
}
