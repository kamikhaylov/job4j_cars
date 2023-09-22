package ru.job4j.cars.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.common.dto.DetailsResponse;
import ru.job4j.cars.common.dto.PostDto;
import ru.job4j.cars.common.dto.PostListResponse;
import ru.job4j.cars.common.model.car.Car;
import ru.job4j.cars.common.model.user.User;
import ru.job4j.cars.service.BrandService;
import ru.job4j.cars.service.CategoryService;
import ru.job4j.cars.service.ColorService;
import ru.job4j.cars.service.EngineService;
import ru.job4j.cars.service.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class PostControllerTest {

    private static final int ID = 0;

    @InjectMocks
    private PostController controller;

    @Mock
    private CategoryService categoryService;
    @Mock
    private BrandService brandService;
    @Mock
    private EngineService engineService;
    @Mock
    private ColorService colorService;
    @Mock
    private PostService postService;
    @Mock
    private Model model;
    @Mock
    private HttpSession httpSession;
    @Mock
    private HttpServletRequest request;
    @Mock
    private User user;
    @Mock
    private MultipartFile file;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void after() {
        Mockito.reset(categoryService, brandService, engineService, colorService,
                postService, model, httpSession, request);
    }

    @Test
    public void whenGetPostList() {
        List<PostListResponse> posts = List.of(new PostListResponse());
        when(postService.getAllIsNotSold()).thenReturn(posts);

        String actual = controller.getPostList(model, httpSession);

        assertNotNull(actual);
        assertEquals("post/post", actual);
        verify(postService).getAllIsNotSold();
        verifyNoMoreInteractions(postService);
    }

    @Test
    public void whenPostListForDay() {
        List<PostListResponse> posts = List.of(new PostListResponse());
        when(postService.getAllForDay()).thenReturn(posts);

        String actual = controller.getPostListForDay(model, httpSession);

        assertNotNull(actual);
        assertEquals("post/post", actual);
        verify(postService).getAllForDay();
        verifyNoMoreInteractions(postService);
    }

    @Test
    public void whenGetPostListWithMileage() {
        List<PostListResponse> posts = List.of(new PostListResponse());
        when(postService.findAllPostByCategoryName("С пробегом")).thenReturn(posts);

        String actual = controller.getPostListWithMileage(model, httpSession);

        assertNotNull(actual);
        assertEquals("post/post", actual);
        verify(postService).findAllPostByCategoryName("С пробегом");
        verifyNoMoreInteractions(postService);
    }

    @Test
    public void whenGetPostListNew() {
        List<PostListResponse> posts = List.of(new PostListResponse());
        when(postService.findAllPostByCategoryName("Новые")).thenReturn(posts);

        String actual = controller.getPostListNew(model, httpSession);

        assertNotNull(actual);
        assertEquals("post/post", actual);
        verify(postService).findAllPostByCategoryName("Новые");
        verifyNoMoreInteractions(postService);
    }

    @Test
    public void whenGetPostListAll() {
        List<PostListResponse> posts = List.of(new PostListResponse());
        when(postService.getAll()).thenReturn(posts);

        String actual = controller.getPostListAll(model, httpSession);

        assertNotNull(actual);
        assertEquals("post/post", actual);
        verify(postService).getAll();
        verifyNoMoreInteractions(postService);
    }

    @Test
    public void whenGetDetails() {
        DetailsResponse details = new DetailsResponse();
        when(postService.getById(ID, ID)).thenReturn(details);
        when(user.getId()).thenReturn(ID);

        String actual = controller.getDetails(model, httpSession, ID);

        assertNotNull(actual);
        assertEquals("post/details", actual);
        verify(postService).getById(ID, ID);
        verifyNoMoreInteractions(postService);
    }

    @Test
    public void whenGetCreate() {
        when(categoryService.getAll()).thenReturn(null);
        when(brandService.getAll()).thenReturn(null);
        when(engineService.getAll()).thenReturn(null);
        when(colorService.getAll()).thenReturn(null);

        String actual = controller.create(model, httpSession);

        assertNotNull(actual);
        assertEquals("post/create", actual);
        verify(categoryService).getAll();
        verify(brandService).getAll();
        verify(engineService).getAll();
        verify(colorService).getAll();
        verifyNoMoreInteractions(categoryService, brandService, engineService, colorService);
    }

    @Test
    public void whenPostCreate() throws IOException {
        when(file.getOriginalFilename()).thenReturn(null);
        when(file.getBytes()).thenReturn(null);

        String actual = controller.create(user, new Car(), "", 0, file, 0);

        assertNotNull(actual);
        assertEquals("redirect:/post/my", actual);
        verify(postService).create(any());
        verify(file).getOriginalFilename();
        verify(file).getBytes();
        verifyNoMoreInteractions(postService, file);
    }

    @Test
    public void whenGetUpdate() {
        when(postService.getById(ID, ID)).thenReturn(null);

        String actual = controller.update(model, httpSession, ID);

        assertNotNull(actual);
        assertEquals("post/update", actual);
        verify(postService).getById(ID, ID);
        verifyNoMoreInteractions(postService);
    }

    @Test
    public void whenPostUpdate() throws IOException {
        String actual = controller.update(model, httpSession, ID, "", 0);

        assertNotNull(actual);
        assertEquals("redirect:/post/details/" + ID, actual);
        verify(postService).update(ID, "", BigDecimal.valueOf(0));
        verifyNoMoreInteractions(postService);
    }

    @Test
    public void whenIsSold() {
        String actual = controller.isSold(model, httpSession, ID);

        assertNotNull(actual);
        assertEquals("redirect:/post/details/" + ID, actual);
        verify(postService).updateIsSold(ID);
        verifyNoMoreInteractions(postService);
    }

    @Test
    public void whenIsNotSold() {
        String actual = controller.isNotSold(model, httpSession, ID);

        assertNotNull(actual);
        assertEquals("redirect:/post/details/" + ID, actual);
        verify(postService).updateIsNotSold(ID);
        verifyNoMoreInteractions(postService);
    }

    @Test
    public void whenDelete() {
        String actual = controller.delete(model, httpSession, ID);

        assertNotNull(actual);
        assertEquals("redirect:/post/my/", actual);
        verify(postService).delete(ID);
        verifyNoMoreInteractions(postService);
    }
}