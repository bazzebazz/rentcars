package com.carsite.rentcars.mapper;

import com.carsite.rentcars.dto.cars.CarsDto;
import com.carsite.rentcars.models.Cars;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface CarMapper extends Converter<Cars, CarsDto> {
    @Override
    CarsDto convert(Cars source);
}
