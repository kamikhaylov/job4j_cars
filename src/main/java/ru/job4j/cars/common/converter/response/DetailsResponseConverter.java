package ru.job4j.cars.common.converter.response;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.job4j.cars.common.dto.DetailsResponse;
import ru.job4j.cars.common.model.post.Post;

/**
 * Конвертер ответа сервиса получения детальной информации объявления
 */
@Component
@AllArgsConstructor
public class DetailsResponseConverter {

    public DetailsResponse convert(Post source, int userId) {
        DetailsResponse result = new DetailsResponse();
        result.setId(source.getId());
        result.setBrand(source.getCar().getBrand().getName());
        result.setModel(source.getCar().getBrand().getModel());
        result.setYear(source.getCar().getYear());
        result.setMileage(source.getCar().getMileage());
        result.setPhoto(source.getPhoto());
        result.setPrice(source.getPrice());
        result.setText(source.getText());
        result.setColor(source.getCar().getColor().getName());
        result.setVin(source.getCar().getVin());
        result.setEngine(source.getCar().getEngine().getName());
        result.setSold(source.getIsSold());
        result.setShowAction(source.getUser().getId() == userId);
        return result;
    }
}
