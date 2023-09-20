package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.common.converter.response.DetailsResponseConverter;
import ru.job4j.cars.common.converter.response.PostResponseConverter;
import ru.job4j.cars.common.dto.DetailsResponse;
import ru.job4j.cars.common.dto.PostDto;
import ru.job4j.cars.common.dto.PostListResponse;
import ru.job4j.cars.common.model.car.Car;
import ru.job4j.cars.common.model.post.Post;
import ru.job4j.cars.repository.post.PostRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для объявлений
 */
@ThreadSafe
@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CarService carService;
    private final PhotoService photoService;
    private final CategoryService categoryService;
    private final PostResponseConverter postResponseConverter;
    private final DetailsResponseConverter detailsResponseConverter;

    @Transactional
    public Post create(PostDto postDto) {
        return postRepository.create(createPost(postDto));
    }

    public List<PostListResponse> getAll() {
        return postRepository.findAll().stream()
                .map(postResponseConverter::convert)
                .collect(Collectors.toList());
    }

    public List<PostListResponse> getAllIsNotSold() {
        return postRepository.findAllIsNotSold().stream()
                .map(postResponseConverter::convert)
                .collect(Collectors.toList());
    }

    public List<PostListResponse> getAllForDay() {
        return postRepository.findAllPostAtLastDay().stream()
                .map(postResponseConverter::convert)
                .collect(Collectors.toList());
    }

    public List<PostListResponse> findAllPostByCategoryName(String name) {
        return postRepository.findAllPostByCategoryName(name).stream()
                .map(postResponseConverter::convert)
                .collect(Collectors.toList());
    }

    public List<PostListResponse> getAllByUserId(int id) {
        return postRepository.findAllPostByUserId(id).stream()
                .map(postResponseConverter::convert)
                .collect(Collectors.toList());
    }

    public DetailsResponse getById(int postId, int userId) {
        return detailsResponseConverter.convert(postRepository.findById(postId).get(), userId);
    }

    @Transactional
    public void update(int id, String text, BigDecimal price) {
        Post post = postRepository.findById(id).get();
        post.setText(text);
        post.setPrice(price);
        postRepository.update(post);
    }

    public void updateIsSold(int id) {
        Post post = postRepository.findById(id).get();
        post.setIsSold(true);
        postRepository.update(post);
    }

    public void updateIsNotSold(int id) {
        Post post = postRepository.findById(id).get();
        post.setIsSold(false);
        postRepository.update(post);
    }

    private Post createPost(PostDto postDto) {
        Car car = carService.create(postDto.getCar());
        Post post = new Post();
        post.setText(postDto.getDescription());
        post.setCreated(LocalDateTime.now());
        post.setUser(postDto.getUser());
        post.setCar(car);
        post.setPrice(postDto.getPrice());
        post.setIsSold(false);
        post.setParticipates(List.of(postDto.getUser()));
        post.setPhoto(photoService.create(postDto.getPhotoDto()));
        post.setCategory(categoryService.getById(postDto.getCategoryId()));
        return post;
    }

}
