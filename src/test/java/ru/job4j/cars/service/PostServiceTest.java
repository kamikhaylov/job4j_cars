package ru.job4j.cars.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.job4j.cars.common.dto.DetailsResponse;
import ru.job4j.cars.common.dto.PhotoDto;
import ru.job4j.cars.common.dto.PostDetailsContext;
import ru.job4j.cars.common.dto.PostDto;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class PostServiceTest {

    private PostService service;

    @Mock
    private PostRepository postRepository;
    @Mock
    private CarService carService;
    @Mock
    private PhotoService photoService;
    @Mock
    private CategoryService categoryService;
    @Mock
    private Mapper<PostDetailsContext, DetailsResponse> detailsResponseModelMapper;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        service = new PostService(postRepository, carService, photoService, categoryService,
                detailsResponseModelMapper);
    }

    @AfterEach
    public void after() {
        Mockito.reset(postRepository, carService, photoService,
                categoryService, detailsResponseModelMapper);
    }

    @Test
    public void whenCreateThenReturnPost() {
        PostDto postDto = createPostDto();
        Post post = createPost();
        when(carService.create(any())).thenReturn(postDto.getCar());
        when(photoService.create(postDto.getPhotoDto())).thenReturn(post.getPhoto());
        when(categoryService.getById(postDto.getCategoryId())).thenReturn(post.getCategory());
        when(postRepository.create(post)).thenReturn(post);

        Post actual = service.create(postDto);

        assertNotNull(actual);
        verify(carService).create(any());
        verify(photoService).create(postDto.getPhotoDto());
        verify(categoryService).getById(postDto.getCategoryId());
        verify(postRepository).create(post);
        verifyNoMoreInteractions(postRepository, carService, photoService,
                categoryService, detailsResponseModelMapper);
    }

    @Test
    public void whenGetByIdThenReturnDetailsResponsePost() {
        int postId = 1;
        int userId = 1;
        Post post = createPost();
        PostDetailsContext postDetailsContext = new PostDetailsContext(post);
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        service.getById(postId, userId);

        verify(postRepository).findById(postId);
        verify(detailsResponseModelMapper).map(postDetailsContext);
        verifyNoMoreInteractions(postRepository, carService, photoService,
                categoryService, detailsResponseModelMapper);
    }

    @Test
    public void whenUpdate() {
        int id = 1;
        String text = "text";
        BigDecimal price = BigDecimal.TEN;
        Post post = createPost();
        when(postRepository.findById(id)).thenReturn(Optional.of(post));

        service.update(id, text, price);

        verify(postRepository).findById(id);
        verify(postRepository).update(post);
        verifyNoMoreInteractions(postRepository, carService, photoService,
                categoryService, detailsResponseModelMapper);
    }

    @Test
    public void whenUpdateIsSold() {
        int id = 1;
        Post post = createPost();
        when(postRepository.findById(id)).thenReturn(Optional.of(post));

        service.updateIsSold(id);

        verify(postRepository).findById(id);
        verify(postRepository).update(post);
        verifyNoMoreInteractions(postRepository, carService, photoService,
                categoryService, detailsResponseModelMapper);
    }

    @Test
    public void whenUpdateIsNotSold() {
        int id = 1;
        Post post = createPost();
        when(postRepository.findById(id)).thenReturn(Optional.of(post));

        service.updateIsNotSold(id);

        verify(postRepository).findById(id);
        verify(postRepository).update(post);
        verifyNoMoreInteractions(postRepository, carService, photoService,
                categoryService, detailsResponseModelMapper);
    }

    @Test
    public void whenDelete() {
        int id = 1;

        service.delete(id);

        verify(postRepository).delete(id);
        verifyNoMoreInteractions(postRepository, carService, photoService,
                categoryService, detailsResponseModelMapper);
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

    private PostDto createPostDto() {
        User user = new User();
        PostDto postDto = new PostDto();
        postDto.setUser(user);
        postDto.setDescription("text");
        postDto.setPhotoDto(new PhotoDto());
        return postDto;
    }
}