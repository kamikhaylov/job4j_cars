package ru.job4j.cars.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.job4j.cars.common.model.post.PriceHistory;
import ru.job4j.cars.repository.post.PriceHistoryRepository;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class PriceHistoryServiceTest {

    @InjectMocks
    private PriceHistoryService service;

    @Mock
    private PriceHistoryRepository repository;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void after() {
        Mockito.reset(repository);
    }

    @Test
    public void whenCreateThenReturnPriceHistory() {
        BigDecimal price = BigDecimal.TEN;
        when(repository.create(any())).thenReturn(new PriceHistory());

        PriceHistory actual = service.create(price);

        assertNotNull(actual);
        verify(repository).create(any());
        verifyNoMoreInteractions(repository);
    }
}