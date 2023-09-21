package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.cars.common.dto.PhotoDto;
import ru.job4j.cars.service.PhotoService;

import java.util.Optional;

/**
 * Контроллер фотографий
 */
@RestController
@AllArgsConstructor
@RequestMapping("/photo")
public class PhotoController {

    private final PhotoService photoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        Optional<PhotoDto> photo = photoService.getById(id);
        if (photo.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(photo.get().getPhoto());
    }
}
