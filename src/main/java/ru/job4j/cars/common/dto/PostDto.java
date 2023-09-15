package ru.job4j.cars.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.job4j.cars.common.model.car.Car;
import ru.job4j.cars.common.model.user.User;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private User user;
    private Car car;
    private String description;
    private BigDecimal price;
    private PhotoDto photoDto;
    private Integer categoryId;


}
