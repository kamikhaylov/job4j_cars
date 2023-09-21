package ru.job4j.cars.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.job4j.cars.common.model.post.Photo;

import java.math.BigDecimal;

/**
 * ДТО ответа сервиса списка объявлений
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostListResponse {
    private Integer id;
    private String brand;
    private String model;
    private Integer year;
    private Integer mileage;
    private BigDecimal price;
    private Photo photo;
    private String text;
}
