package com.carsite.rentcars.mapper.category;

import com.carsite.rentcars.dto.cars.CategoryDto;
import com.carsite.rentcars.models.Category;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface CategoryDtoMapper extends Converter<CategoryDto, Category> {
    @Override
    Category convert(CategoryDto source);
}
