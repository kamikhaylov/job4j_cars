package ru.job4j.cars.common.converter.response;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.job4j.cars.common.dto.PostListResponse;
import ru.job4j.cars.common.model.post.Post;

@Component
@AllArgsConstructor
public class PostResponseConverter {

    public PostListResponse convert(Post source) {
        PostListResponse result = new PostListResponse();
        result.setId(source.getId());
        result.setBrand(source.getCar().getBrand().getName());
        result.setModel(source.getCar().getBrand().getModel());
        result.setYear(source.getCar().getYear());
        result.setMileage(source.getCar().getMileage());
        result.setPhoto(source.getPhoto());
        result.setPrice(source.getPrice());
        result.setText(source.getText());
        return result;
    }
}