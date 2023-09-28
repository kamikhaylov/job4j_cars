package ru.job4j.cars.common.mapper.response;

import org.junit.jupiter.api.Test;
import ru.job4j.cars.common.dto.PostListResponse;
import ru.job4j.cars.common.model.car.Brand;
import ru.job4j.cars.common.model.car.Car;
import ru.job4j.cars.common.model.post.Photo;
import ru.job4j.cars.common.model.post.Post;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PostResponseModelMapperTest {

    private final Mapper<Post, PostListResponse> postResponseModelMapper =
            new PostResponseModelMapper();

    @Test
    public void whenMapThenReturnPostListResponse() {
        PostListResponse expected = createPostListResponse();
        PostListResponse actual = postResponseModelMapper.map(createPost());

        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getBrand(), actual.getBrand());
        assertEquals(expected.getModel(), actual.getModel());
        assertEquals(expected.getYear(), actual.getYear());
        assertEquals(expected.getMileage(), actual.getMileage());
        assertEquals(expected.getPhoto(), actual.getPhoto());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getText(), actual.getText());
    }

    private Post createPost() {
        Post post = new Post();
        post.setId(1);
        post.setCar(createCar());
        post.setPhoto(new Photo(1, "namePhoto", "pathPhoto"));
        post.setPrice(BigDecimal.TEN);
        post.setText("description");
        return post;
    }

    private Car createCar() {
        Car car = new Car();
        car.setId(1);
        car.setBrand(new Brand(1, "nameBrand", "model"));
        car.setYear(2023);
        car.setMileage(10);
        return car;
    }

    private PostListResponse createPostListResponse() {
        PostListResponse postListResponse = new PostListResponse();
        postListResponse.setId(1);
        postListResponse.setBrand("nameBrand");
        postListResponse.setModel("model");
        postListResponse.setYear(2023);
        postListResponse.setMileage(10);
        postListResponse.setPhoto(new Photo(1, "namePhoto", "pathPhoto"));
        postListResponse.setPrice(BigDecimal.TEN);
        postListResponse.setText("description");
        return postListResponse;
    }
}