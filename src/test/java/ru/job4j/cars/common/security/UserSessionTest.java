package ru.job4j.cars.common.security;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import ru.job4j.cars.common.model.user.User;
import ru.job4j.cars.common.security.UserSession;

import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserSessionTest {

    @Mock
    private Model model;
    @Mock
    private HttpSession httpSession;
    @InjectMocks
    private UserSession userSession;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void after() {
        Mockito.reset(model, httpSession);
    }

    @Test
    public void whenGetUser() {
        User user = new User(0, "login", "password", "name");
        when(httpSession.getAttribute("user")).thenReturn(user);

        User actual = userSession.getUser(model, httpSession);

        Assertions.assertEquals(actual, user);
        verify(model).addAttribute("user", user);
    }

    @Test
    public void whenGetGuest() {
        User user = new User(0, "guest", null, null);
        when(httpSession.getAttribute("user")).thenReturn(null);

        User actual = userSession.getUser(model, httpSession);

        Assertions.assertEquals(actual, user);
        verify(model).addAttribute("user", user);
    }
}