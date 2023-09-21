package ru.job4j.cars.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.job4j.cars.common.model.post.Category;
import ru.job4j.cars.repository.post.CategoryRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class CategoryServiceTest {

    @InjectMocks
    private CategoryService service;

    @Mock
    private CategoryRepository repository;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void after() {
        Mockito.reset(repository);
    }

    @Test
    public void whenGetAllThenReturnCategories() {
        List<Category> categories = List.of(new Category());
        when(repository.findAll()).thenReturn(categories);

        List<Category> actual = service.getAll();

        assertNotNull(actual);
        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void whenGetByIdThenReturnCategory() {
        int id = 1;
        Optional<Category> category = Optional.of(new Category());
        when(repository.findById(id)).thenReturn(category);

        Category actual = service.getById(id);

        assertNotNull(actual);
        verify(repository).findById(id);
        verifyNoMoreInteractions(repository);
    }
}