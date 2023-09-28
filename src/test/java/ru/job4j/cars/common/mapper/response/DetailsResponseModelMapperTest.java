package ru.job4j.cars.common.mapper.response;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.job4j.cars.common.dto.DetailsResponse;
import ru.job4j.cars.common.dto.PostDetailsContext;
import ru.job4j.cars.common.model.car.Brand;
import ru.job4j.cars.common.model.car.Car;
import ru.job4j.cars.common.model.car.Color;
import ru.job4j.cars.common.model.car.Engine;
import ru.job4j.cars.common.model.post.Photo;
import ru.job4j.cars.common.model.post.Post;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DetailsResponseModelMapperTest {

    private final Mapper<PostDetailsContext, DetailsResponse> detailsResponseModelMapper =
            new DetailsResponseModelMapper();

    private static Stream<Arguments> showActionProvider() {
        return Stream.of(
                Arguments.of(true),
                Arguments.of(false)
        );
    }

    @ParameterizedTest
    @MethodSource("showActionProvider")
    public void whenMapThenReturnDetailsResponse(boolean showAction) {
        DetailsResponse expected = createDetailsResponse(showAction);
        DetailsResponse actual = detailsResponseModelMapper
                .map(new PostDetailsContext(createPost(), showAction));

        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getBrand(), actual.getBrand());
        assertEquals(expected.getModel(), actual.getModel());
        assertEquals(expected.getYear(), actual.getYear());
        assertEquals(expected.getMileage(), actual.getMileage());
        assertEquals(expected.getPhoto(), actual.getPhoto());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getColor(), actual.getColor());
        assertEquals(expected.getVin(), actual.getVin());
        assertEquals(expected.getEngine(), actual.getEngine());
        assertEquals(expected.getEngine(), actual.getEngine());
        assertEquals(showAction, actual.isShowAction());
    }

    private Post createPost() {
        Post post = new Post();
        post.setId(1);
        post.setCar(createCar());
        post.setPhoto(new Photo(1, "namePhoto", "pathPhoto"));
        post.setPrice(BigDecimal.TEN);
        post.setText("description");
        post.setIsSold(false);
        return post;
    }

    private Car createCar() {
        Car car = new Car();
        car.setId(1);
        car.setBrand(new Brand(1, "nameBrand", "model"));
        car.setYear(2023);
        car.setMileage(10);
        car.setColor(new Color(1, "red"));
        car.setVin("123");
        car.setEngine(new Engine(1, "1.8"));
        return car;
    }

    private DetailsResponse createDetailsResponse(boolean showAction) {
        DetailsResponse detailsResponse = new DetailsResponse();
        detailsResponse.setId(1);
        detailsResponse.setBrand("nameBrand");
        detailsResponse.setModel("model");
        detailsResponse.setYear(2023);
        detailsResponse.setMileage(10);
        detailsResponse.setPhoto(new Photo(1, "namePhoto", "pathPhoto"));
        detailsResponse.setPrice(BigDecimal.TEN);
        detailsResponse.setText("description");
        detailsResponse.setColor("red");
        detailsResponse.setVin("123");
        detailsResponse.setEngine("1.8");
        detailsResponse.setSold(false);
        detailsResponse.setShowAction(showAction);
        return detailsResponse;
    }
}