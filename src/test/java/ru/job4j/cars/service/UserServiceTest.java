package ru.job4j.cars.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.job4j.cars.common.model.user.User;
import ru.job4j.cars.repository.user.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void after() {
        Mockito.reset(repository);
    }

    @Test
    public void whenCreateThenReturnUser() {
        User user = new User();
        when(repository.create(user)).thenReturn(user);

        User actual = service.create(user);

        assertNotNull(actual);
        verify(repository).create(user);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void whenFindByLoginThenReturnUser() {
        User user = new User();
        when(repository.findByLogin(user.getLogin())).thenReturn(Optional.of(user));

        Optional<User> actual = service.findByLogin(user);

        assertNotNull(actual.get());
        verify(repository).findByLogin(user.getLogin());
        verifyNoMoreInteractions(repository);
    }
}