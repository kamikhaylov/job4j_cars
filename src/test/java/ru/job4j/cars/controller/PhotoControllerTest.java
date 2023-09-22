package ru.job4j.cars.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import ru.job4j.cars.common.dto.PhotoDto;
import ru.job4j.cars.service.PhotoService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class PhotoControllerTest {

    @InjectMocks
    private PhotoController controller;

    @Mock
    private PhotoService service;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void after() {
        Mockito.reset(service);
    }

    @Test
    public void whenGetByIdThenPhone() {
        int id = 1;
        PhotoDto photoDto = new PhotoDto();
        when(service.getById(id)).thenReturn(Optional.of(photoDto));

        ResponseEntity<?> actual = controller.getById(id);

        assertNotNull(actual);
        verify(service).getById(id);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void whenGetByIdThenEmpty() {
        int id = 1;
        when(service.getById(id)).thenReturn(Optional.empty());

        ResponseEntity<?> actual = controller.getById(id);

        assertNotNull(actual);
        verify(service).getById(id);
        verifyNoMoreInteractions(service);
    }
}