package com.carsite.rentcars.services;

import com.carsite.rentcars.dto.cars.CarsDto;
import com.carsite.rentcars.dto.cars.CategoryDto;
import com.carsite.rentcars.models.Cars;
import com.carsite.rentcars.models.Category;
import com.carsite.rentcars.repository.CarRepository;
import com.carsite.rentcars.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CategoryRepository categoryRepository;
    private final ConversionService conversionService;

    @Autowired
    public CarService(CarRepository carRepository, ConversionService conversionService,
            CategoryRepository categoryRepository) {
        this.carRepository = carRepository;
        this.conversionService = conversionService;
        this.categoryRepository = categoryRepository;
    }

    public List<CarsDto> getAllCars() {
        return conversionService.convert(carRepository.findAll(), List.class);
    }

    public CarsDto getCarById(Long id) {
        Optional<Cars> result = carRepository.findById(id);
        return conversionService.convert(result.get(), CarsDto.class);
    }

    public CarsDto saveCar(CarsDto carsDto) {
        Category category = categoryRepository.findById(carsDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Cars transformed = conversionService.convert(carsDto, Cars.class);
        transformed.setCategory(category);

        Cars result = carRepository.save(transformed);
        carsDto.setCategoryId(category.getId());

        CarsDto resultDto = conversionService.convert(result, CarsDto.class);

        // Establecer la categoría en el CarsDto resultante
        resultDto.setCategory(conversionService.convert(category, CategoryDto.class));
        return resultDto;

    }

    public CarsDto updateCar(CarsDto carsDto, Long id) {
        Cars existingCar = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));

        Category category = categoryRepository.findById(carsDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Actualizar los campos del Car existente con los valores del DTO
        BeanUtils.copyProperties(carsDto, existingCar, "id", "category");
        existingCar.setCategory(category);

        Cars result = carRepository.save(existingCar);

        CarsDto resultDto = conversionService.convert(result, CarsDto.class);
        resultDto.setCategory(conversionService.convert(category, CategoryDto.class));

        return resultDto;
    }

    public void deleteCar(Long id) {
        // Primero, eliminar la relación en la tabla intermedia
        carRepository.deleteById(id);

        // Luego, eliminar el coche
        Cars existingCar = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        carRepository.delete(existingCar);
    }

}
