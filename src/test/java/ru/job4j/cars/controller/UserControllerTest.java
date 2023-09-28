package ru.job4j.cars.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import ru.job4j.cars.common.model.user.User;
import ru.job4j.cars.common.security.UserSession;
import ru.job4j.cars.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class UserControllerTest {

    private User user;
    private UserController controller;

    @Mock
    private UserService userService;
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
        user = new User(0, "login", "password", "name");
        when(userService.findByLogin(user)).thenReturn(Optional.of(user));
        controller = new UserController(userService, userSession);
    }

    @AfterEach
    public void after() {
        Mockito.reset(userService, model, httpSession, request);
    }

    @Test
    public void whenRegistration() {
        String actual = controller.registration(model, httpSession, false, false);

        assertNotNull(actual);
        assertEquals("user/registration", actual);
    }

    @Test
    public void whenCreateThenFail() {
        String actual = controller.create(model, user);

        assertNotNull(actual);
        assertEquals("redirect:/user/registration?fail=true", actual);
    }

    @Test
    public void whenCreateThenSuccess() {
        when(userService.findByLogin(user)).thenReturn(Optional.empty());

        String actual = controller.create(model, user);

        assertNotNull(actual);
        assertEquals("redirect:/user/registration?success=true", actual);
    }

    @Test
    public void whenSuccess() {
        String actual = controller.success(user);

        assertNotNull(actual);
        assertEquals("user/success", actual);
    }

    @Test
    public void whenFail() {
        String actual = controller.fail(user);

        assertNotNull(actual);
        assertEquals("user/fail", actual);
    }

    @Test
    public void whenAuthorization() {
        String actual = controller.authorization(model, httpSession, false);

        assertNotNull(actual);
        assertEquals("user/authorization", actual);
    }

    @Test
    public void whenLoginFail() {
        when(userService.findByLoginAndPassword(user)).thenReturn(Optional.empty());
        String actual = controller.login(user, request);

        assertNotNull(actual);
        assertEquals("redirect:/user/authorization?fail=true", actual);
    }

    @Test
    public void whenLoginSuccess() {
        when(userService.findByLoginAndPassword(user)).thenReturn(Optional.of(user));
        when(request.getSession()).thenReturn(httpSession);
        String actual = controller.login(user, request);

        assertNotNull(actual);
        assertEquals("redirect:/posts/list", actual);
    }

    @Test
    public void whenLogout() {
        String actual = controller.logout(httpSession);

        assertNotNull(actual);
        assertEquals("redirect:/user/authorization", actual);
    }
}