package ru.job4j.cars.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import ru.job4j.cars.common.dto.PostListResponse;
import ru.job4j.cars.common.security.UserSession;
import ru.job4j.cars.service.PostsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class PostsControllerTest {

    private PostsController controller;

    @Mock
    private PostsService service;
    @Mock
    private Model model;
    @Mock
    private HttpSession httpSession;
    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private UserSession userSession;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        controller = new PostsController(service, userSession);
    }

    @AfterEach
    public void after() {
        Mockito.reset(service, model, httpSession, request);
    }

    @Test
    public void whenGetPostList() {
        List<PostListResponse> posts = List.of(new PostListResponse());
        when(service.getAllIsNotSold()).thenReturn(posts);

        String actual = controller.getPostList(model, httpSession);

        assertNotNull(actual);
        assertEquals("post/post", actual);
        verify(service).getAllIsNotSold();
        verifyNoMoreInteractions(service);
    }

    @Test
    public void whenPostListForDay() {
        List<PostListResponse> posts = List.of(new PostListResponse());
        when(service.getAllForDay()).thenReturn(posts);

        String actual = controller.getPostListForDay(model, httpSession);

        assertNotNull(actual);
        assertEquals("post/post", actual);
        verify(service).getAllForDay();
        verifyNoMoreInteractions(service);
    }

    @Test
    public void whenGetPostListWithMileage() {
        List<PostListResponse> posts = List.of(new PostListResponse());
        when(service.findAllPostByCategoryName("С пробегом")).thenReturn(posts);

        String actual = controller.getPostListWithMileage(model, httpSession);

        assertNotNull(actual);
        assertEquals("post/post", actual);
        verify(service).findAllPostByCategoryName("С пробегом");
        verifyNoMoreInteractions(service);
    }

    @Test
    public void whenGetPostListNew() {
        List<PostListResponse> posts = List.of(new PostListResponse());
        when(service.findAllPostByCategoryName("Новые")).thenReturn(posts);

        String actual = controller.getPostListNew(model, httpSession);

        assertNotNull(actual);
        assertEquals("post/post", actual);
        verify(service).findAllPostByCategoryName("Новые");
        verifyNoMoreInteractions(service);
    }

    @Test
    public void whenGetPostListAll() {
        List<PostListResponse> posts = List.of(new PostListResponse());
        when(service.getAll()).thenReturn(posts);

        String actual = controller.getPostListAll(model, httpSession);

        assertNotNull(actual);
        assertEquals("post/post", actual);
        verify(service).getAll();
        verifyNoMoreInteractions(service);
    }
}