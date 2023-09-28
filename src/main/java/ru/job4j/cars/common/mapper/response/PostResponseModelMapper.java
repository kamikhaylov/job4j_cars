package ru.job4j.cars.common.mapper.response;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;
import ru.job4j.cars.common.dto.PostListResponse;
import ru.job4j.cars.common.model.post.Post;

/**
 * Маппер ответа сервиса получения списка объявлений
 */
@Component
public class PostResponseModelMapper implements Mapper<Post, PostListResponse> {

    private final ModelMapper mapper;

    public PostResponseModelMapper() {
        this.mapper = new ModelMapper();
        this.mapper.getConfiguration().setSkipNullEnabled(true);
        TypeMap<Post, PostListResponse> propertyMapper =
                this.mapper.createTypeMap(Post.class, PostListResponse.class);
        propertyMapper.addMapping(Post::getId, PostListResponse::setId);
        propertyMapper.addMappings(
                mapper -> mapper.map(post ->
                        post.getCar().getBrand().getName(), PostListResponse::setBrand)
        );
        propertyMapper.addMappings(
                mapper -> mapper.map(post ->
                        post.getCar().getBrand().getModel(), PostListResponse::setModel)
        );
        propertyMapper.addMappings(
                mapper -> mapper.map(post ->
                        post.getCar().getYear(), PostListResponse::setYear)
        );
        propertyMapper.addMappings(
                mapper -> mapper.map(post ->
                        post.getCar().getMileage(), PostListResponse::setMileage)
        );
        propertyMapper.addMapping(Post::getPhoto, PostListResponse::setPhoto);
        propertyMapper.addMapping(Post::getPrice, PostListResponse::setPrice);
        propertyMapper.addMapping(Post::getText, PostListResponse::setText);
    }

    @Override
    public PostListResponse map(Post post) {
        return this.mapper.map(post, PostListResponse.class);
    }
}
