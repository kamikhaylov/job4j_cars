package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.common.dto.PhotoDto;
import ru.job4j.cars.common.model.post.Photo;
import ru.job4j.cars.repository.post.PhotoRepository;

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
}
