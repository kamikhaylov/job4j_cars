package ru.job4j.cars.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
}
