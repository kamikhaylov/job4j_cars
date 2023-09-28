package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.common.dto.PostListResponse;
import ru.job4j.cars.common.mapper.response.Mapper;
import ru.job4j.cars.common.model.post.Post;
import ru.job4j.cars.repository.post.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для объявлений
 */
@ThreadSafe
@Service
@AllArgsConstructor
public class PostsService {

    private final PostRepository postRepository;
    private final Mapper<Post, PostListResponse> postResponseModelMapper;

    public List<PostListResponse> getAll() {
        return postRepository.findAll().stream()
                .map(postResponseModelMapper::map)
                .collect(Collectors.toList());
    }

    public List<PostListResponse> getAllIsNotSold() {
        return postRepository.findAllIsNotSold().stream()
                .map(postResponseModelMapper::map)
                .collect(Collectors.toList());
    }

    public List<PostListResponse> getAllForDay() {
        return postRepository.findAllPostAtLastDay().stream()
                .map(postResponseModelMapper::map)
                .collect(Collectors.toList());
    }

    public List<PostListResponse> findAllPostByCategoryName(String name) {
        return postRepository.findAllPostByCategoryName(name).stream()
                .map(postResponseModelMapper::map)
                .collect(Collectors.toList());
    }

    public List<PostListResponse> getAllByUserId(int id) {
        return postRepository.findAllPostByUserId(id).stream()
                .map(postResponseModelMapper::map)
                .collect(Collectors.toList());
    }
}
