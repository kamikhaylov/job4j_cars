package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.common.dto.PostDto;

/**
 * Сервис для объявлений
 */
@ThreadSafe
@Service
@AllArgsConstructor
public class PostService {

    public boolean create(PostDto post) {

        return true;
    }

}
