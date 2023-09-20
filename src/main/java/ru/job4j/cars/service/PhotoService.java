package ru.job4j.cars.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.job4j.cars.common.dto.PhotoDto;
import ru.job4j.cars.common.model.post.Photo;
import ru.job4j.cars.repository.post.PhotoRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * Сервис для фотографий
 */
@ThreadSafe
@Service
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final String directory;
    private final String defaultImage;

    public PhotoService(
            PhotoRepository photoRepository,
            @Value("${file.directory}") String directory,
            @Value("${file.default.name}") String defaultImage) {
        this.photoRepository = photoRepository;
        this.directory = directory;
        this.defaultImage = defaultImage;
    }

    public Photo create(PhotoDto photoDto) {
        return isNotEmpty(photoDto.getName())
                ? photoRepository.create(createPhoto(photoDto))
                : photoRepository.getDefault();
    }

    public Optional<PhotoDto> getById(int id) {
        Optional<Photo> photoOptional = photoRepository.findById(id);
        if (photoOptional.isEmpty()) {
            return Optional.empty();
        }
        byte[] content = readFileAsBytes(photoOptional.get().getPath());
        return Optional.of(new PhotoDto(photoOptional.get().getName(), content));
    }

    private String createPath(String name) {
        return directory + java.io.File.separator + UUID.randomUUID() + name;
    }

    private void writeFileBytes(String path, byte[] photo) {
        try {
            Files.write(Path.of(path), photo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] readFileAsBytes(String path) {
        try {
            return Files.readAllBytes(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Photo createPhoto(PhotoDto photoDto) {
        Photo photo = new Photo();
        String path = createPath(photoDto.getName());
        writeFileBytes(path, photoDto.getPhoto());
        photo.setName(photoDto.getName());
        photo.setPath(path);
        return photo;
    }
}
