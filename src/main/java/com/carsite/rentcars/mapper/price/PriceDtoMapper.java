package com.carsite.rentcars.mapper.price;

import com.carsite.rentcars.dto.cars.PriceDto;
import com.carsite.rentcars.models.Price;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface PriceDtoMapper extends Converter<PriceDto, Price> {
    @Override
    Price convert(PriceDto source);
}
