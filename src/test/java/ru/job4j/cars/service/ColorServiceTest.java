package ru.job4j.cars.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.job4j.cars.common.model.car.Color;
import ru.job4j.cars.repository.car.ColorRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class ColorServiceTest {

    @InjectMocks
    private ColorService service;

    @Mock
    private ColorRepository repository;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void after() {
        Mockito.reset(repository);
    }

    @Test
    public void whenGetAllThenReturnColors() {
        List<Color> colors = List.of(new Color());
        when(repository.findAll()).thenReturn(colors);

        List<Color> actual = service.getAll();

        assertNotNull(actual);
        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }
}