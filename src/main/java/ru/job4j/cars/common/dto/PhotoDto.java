package ru.job4j.cars.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ДТО фотографии
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoDto {
    private String name;
    private byte[] photo;
}
