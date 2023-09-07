package ru.job4j.cars;

import net.jcip.annotations.ThreadSafe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

/**
 * Запуск приложения
 */
@ThreadSafe
@SpringBootApplication
public class Application {
    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        LOGGER.info("Go to http://localhost:8080/cars");
    }
}