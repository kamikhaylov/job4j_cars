package ru.job4j.cars.common.mapper.response;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;
import ru.job4j.cars.common.dto.DetailsResponse;
import ru.job4j.cars.common.dto.PostDetailsContext;

/**
 * Маппер ответа сервиса получения детальной информации объявления
 */
@Component
public class DetailsResponseModelMapper implements Mapper<PostDetailsContext, DetailsResponse> {

    private final ModelMapper mapper;

    public DetailsResponseModelMapper() {
        this.mapper = new ModelMapper();
        this.mapper.getConfiguration().setSkipNullEnabled(true);
        TypeMap<PostDetailsContext, DetailsResponse> propertyMapper =
                this.mapper.createTypeMap(PostDetailsContext.class, DetailsResponse.class);
        propertyMapper.addMappings(
                mapper -> mapper.map(postDetailsContext ->
                        postDetailsContext.getPost().getId(), DetailsResponse::setId)
        );
        propertyMapper.addMappings(
                mapper -> mapper.map(postDetailsContext ->
                        postDetailsContext.getPost().getCar().getBrand().getName(),
                        DetailsResponse::setBrand)
        );
        propertyMapper.addMappings(
                mapper -> mapper.map(postDetailsContext ->
                        postDetailsContext.getPost().getCar().getBrand().getModel(),
                        DetailsResponse::setModel)
        );
        propertyMapper.addMappings(
                mapper -> mapper.map(postDetailsContext ->
                        postDetailsContext.getPost().getCar().getYear(),
                        DetailsResponse::setYear)
        );
        propertyMapper.addMappings(
                mapper -> mapper.map(postDetailsContext ->
                        postDetailsContext.getPost().getCar().getMileage(),
                        DetailsResponse::setMileage)
        );
        propertyMapper.addMappings(
                mapper -> mapper.map(postDetailsContext ->
                        postDetailsContext.getPost().getPhoto(),
                        DetailsResponse::setPhoto)
        );
        propertyMapper.addMappings(
                mapper -> mapper.map(postDetailsContext ->
                        postDetailsContext.getPost().getPrice(),
                        DetailsResponse::setPrice)
        );
        propertyMapper.addMappings(
                mapper -> mapper.map(postDetailsContext ->
                        postDetailsContext.getPost().getText(),
                        DetailsResponse::setText)
        );
        propertyMapper.addMappings(
                mapper -> mapper.map(postDetailsContext ->
                        postDetailsContext.getPost().getCar().getColor().getName(),
                        DetailsResponse::setColor)
        );
        propertyMapper.addMappings(
                mapper -> mapper.map(postDetailsContext ->
                        postDetailsContext.getPost().getCar().getVin(),
                        DetailsResponse::setVin)
        );
        propertyMapper.addMappings(
                mapper -> mapper.map(postDetailsContext ->
                        postDetailsContext.getPost().getCar().getEngine().getName(),
                        DetailsResponse::setEngine)
        );
        propertyMapper.addMappings(
                mapper -> mapper.map(postDetailsContext ->
                        postDetailsContext.getPost().getIsSold(),
                        DetailsResponse::setSold)
        );
        propertyMapper.addMapping(
                PostDetailsContext::isShowAction,
                DetailsResponse::setShowAction);
    }

    @Override
    public DetailsResponse map(PostDetailsContext post) {
        return this.mapper.map(post, DetailsResponse.class);
    }
}
