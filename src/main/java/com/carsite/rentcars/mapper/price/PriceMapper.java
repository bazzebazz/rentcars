package com.carsite.rentcars.mapper.price;

import com.carsite.rentcars.dto.cars.PriceDto;
import com.carsite.rentcars.models.Price;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface PriceMapper extends Converter<Price, PriceDto> {
    @Override
    PriceDto convert(Price source);
}
