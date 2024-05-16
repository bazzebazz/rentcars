package com.carsite.rentcars.mapper;

import com.carsite.rentcars.dto.cars.CarsDto;
import com.carsite.rentcars.models.Cars;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarsMapper extends Converter<List<Cars>, List<CarsDto>> {
    @Override
    List<CarsDto> convert(List<Cars> source);
}
