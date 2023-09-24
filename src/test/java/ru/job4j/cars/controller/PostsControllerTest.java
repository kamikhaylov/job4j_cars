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
import ru.job4j.cars.service.PostService;

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
    private PostService postService;
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
        controller = new PostsController(postService, userSession);
    }

    @AfterEach
    public void after() {
        Mockito.reset(postService, model, httpSession, request);
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
}