package ru.job4j.cars.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.job4j.cars.common.model.car.Brand;
import ru.job4j.cars.repository.car.BrandRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class BrandServiceTest {

    @InjectMocks
    private BrandService service;

    @Mock
    private BrandRepository repository;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void after() {
        Mockito.reset(repository);
    }

    @Test
    public void whenGetAllThenReturnBrands() {
        List<Brand> brands = List.of(new Brand());
        when(repository.findAll()).thenReturn(brands);

        List<Brand> actual = service.getAll();

        assertNotNull(actual);
        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }
}