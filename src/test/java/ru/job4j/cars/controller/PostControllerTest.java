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
import ru.job4j.cars.common.model.car.Car;
import ru.job4j.cars.common.model.user.User;
import ru.job4j.cars.common.security.UserSession;
import ru.job4j.cars.service.BrandService;
import ru.job4j.cars.service.CategoryService;
import ru.job4j.cars.service.ColorService;
import ru.job4j.cars.service.EngineService;
import ru.job4j.cars.service.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class PostControllerTest {

    private static final int ID = 0;

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
    @InjectMocks
    private UserSession userSession;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        controller = new PostController(categoryService, brandService,
                engineService, colorService, postService, userSession);
    }

    @AfterEach
    public void after() {
        Mockito.reset(categoryService, brandService, engineService, colorService,
                postService, model, httpSession, request);
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
        assertEquals("redirect:/posts/my", actual);
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
        assertEquals("redirect:/posts/my/", actual);
        verify(postService).delete(ID);
        verifyNoMoreInteractions(postService);
    }
}