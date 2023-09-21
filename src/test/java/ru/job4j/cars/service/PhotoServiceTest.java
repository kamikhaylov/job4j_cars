package ru.job4j.cars.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.job4j.cars.common.dto.PhotoDto;
import ru.job4j.cars.common.model.post.Photo;
import ru.job4j.cars.repository.post.PhotoRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class PhotoServiceTest {

    private static final String DIRECTORY = "src/main/resources/file";

    private PhotoService service;

    @Mock
    private PhotoRepository repository;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        service = new PhotoService(repository, DIRECTORY);
    }

    @AfterEach
    public void after() {
        Mockito.reset(repository);
    }

    @Test
    public void whenIsEmptyCreateThenReturnDefaultPhoto() {
        PhotoDto photoDto = new PhotoDto();
        when(repository.getDefault()).thenReturn(new Photo());

        Photo actual = service.create(photoDto);

        assertNotNull(actual);
        verify(repository).getDefault();
        verifyNoMoreInteractions(repository);
    }
}