package ru.job4j.cars.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.job4j.cars.common.model.car.Engine;
import ru.job4j.cars.repository.car.EngineRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class EngineServiceTest {

    @InjectMocks
    private EngineService service;

    @Mock
    private EngineRepository repository;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void after() {
        Mockito.reset(repository);
    }

    @Test
    public void whenGetAllThenReturnEngines() {
        List<Engine> engines = List.of(new Engine());
        when(repository.findAll()).thenReturn(engines);

        List<Engine> actual = service.getAll();

        assertNotNull(actual);
        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }
}