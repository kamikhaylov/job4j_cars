package ru.job4j.cars.common.mapper.response;

/**
 * Маппер модели
 */
public interface Mapper<P, T> {

    /**
     * Маппер объекта типа P в объект типа T
     * @param p - источник
     * @return полученный объект
     */
    T map(P p);
}
