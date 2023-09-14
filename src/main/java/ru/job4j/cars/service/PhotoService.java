package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.common.dto.PhotoDto;
import ru.job4j.cars.common.model.post.Photo;
import ru.job4j.cars.repository.post.PhotoRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Сервис для фотографий
 */
@ThreadSafe
@Service
@AllArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;

    public Photo create(PhotoDto photoDto) {
        Photo photo = new Photo();
        return photoRepository.getDefault();
    }

    public Optional<PhotoDto> getById(int id) {
        Optional<Photo> photoOptional = photoRepository.findById(id);
        if (photoOptional.isEmpty()) {
            return Optional.empty();
        }
        byte[] content = readFileAsBytes(photoOptional.get().getPath());
        return Optional.of(new PhotoDto(photoOptional.get().getName(), content));
    }

    private byte[] readFileAsBytes(String path) {
        try {
            return Files.readAllBytes(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
