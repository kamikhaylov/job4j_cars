package ru.job4j.cars.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.job4j.cars.common.dto.PostListResponse;
import ru.job4j.cars.common.mapper.response.Mapper;
import ru.job4j.cars.common.model.car.Brand;
import ru.job4j.cars.common.model.car.Car;
import ru.job4j.cars.common.model.car.Color;
import ru.job4j.cars.common.model.car.Engine;
import ru.job4j.cars.common.model.post.Category;
import ru.job4j.cars.common.model.post.Photo;
import ru.job4j.cars.common.model.post.Post;
import ru.job4j.cars.common.model.user.User;
import ru.job4j.cars.repository.post.PostRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class PostsServiceTest {

    private PostsService service;

    @Mock
    private PostRepository postRepository;
    @Mock
    private Mapper<Post, PostListResponse> postResponseModelMapper;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        service = new PostsService(postRepository, postResponseModelMapper);
    }

    @AfterEach
    public void after() {
        Mockito.reset(postRepository, postResponseModelMapper);
    }

    @Test
    public void whenGetAllThenReturnPosts() {
        Post post = createPost();
        when(postRepository.findAll()).thenReturn(List.of(post));
        when(postResponseModelMapper.map(post)).thenReturn(new PostListResponse());

        List<PostListResponse> actual = service.getAll();

        assertNotNull(actual);
        verify(postRepository).findAll();
        verify(postResponseModelMapper).map(post);
        verifyNoMoreInteractions(postRepository, postResponseModelMapper);
    }

    @Test
    public void whenGetAllIsNotSoldThenReturnPosts() {
        Post post = createPost();
        when(postRepository.findAllIsNotSold()).thenReturn(List.of(post));
        when(postResponseModelMapper.map(post)).thenReturn(new PostListResponse());

        List<PostListResponse> actual = service.getAllIsNotSold();

        assertNotNull(actual);
        verify(postRepository).findAllIsNotSold();
        verify(postResponseModelMapper).map(post);
        verifyNoMoreInteractions(postRepository, postResponseModelMapper);
    }

    @Test
    public void whenGetAllForDayThenReturnPosts() {
        Post post = createPost();
        when(postRepository.findAllPostAtLastDay()).thenReturn(List.of(post));

        List<PostListResponse> actual = service.getAllForDay();

        assertNotNull(actual);
        verify(postRepository).findAllPostAtLastDay();
        verify(postResponseModelMapper).map(post);
        verifyNoMoreInteractions(postRepository, postResponseModelMapper);
    }

    @Test
    public void whenFindAllPostByCategoryNameThenReturnPosts() {
        String category = "С пробегом";
        Post post = createPost();
        when(postRepository.findAllPostByCategoryName(category)).thenReturn(List.of(post));
        when(postRepository.findAllPostByCategoryName(category)).thenReturn(List.of(post));

        List<PostListResponse> actual = service.findAllPostByCategoryName(category);

        assertNotNull(actual);
        verify(postRepository).findAllPostByCategoryName(category);
        verify(postResponseModelMapper).map(post);
        verifyNoMoreInteractions(postRepository, postResponseModelMapper);
    }

    @Test
    public void whenGetAllByUserIdThenReturnPosts() {
        int id = 1;
        Post post = createPost();
        when(postRepository.findAllPostByUserId(id)).thenReturn(List.of(post));

        List<PostListResponse> actual = service.getAllByUserId(id);

        assertNotNull(actual);
        verify(postRepository).findAllPostByUserId(id);
        verify(postResponseModelMapper).map(post);
        verifyNoMoreInteractions(postRepository, postResponseModelMapper);
    }

    private Post createPost() {
        User user = new User();
        Car car = new Car();
        car.setBrand(new Brand());
        car.setColor(new Color());
        car.setEngine(new Engine());
        Post post = new Post();
        post.setText("text");
        post.setCreated(LocalDateTime.now());
        post.setUser(user);
        post.setCar(car);
        post.setPrice(BigDecimal.TEN);
        post.setIsSold(false);
        post.setParticipates(List.of(user));
        post.setPhoto(new Photo());
        post.setCategory(new Category());
        return post;
    }
}
