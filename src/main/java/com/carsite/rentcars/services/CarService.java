package com.carsite.rentcars.services;

import com.carsite.rentcars.dto.cars.CarsDto;
import com.carsite.rentcars.dto.cars.CategoryDto;
import com.carsite.rentcars.models.Cars;
import com.carsite.rentcars.models.CarsCategory;
import com.carsite.rentcars.models.Category;
import com.carsite.rentcars.repository.CarRepository;
import com.carsite.rentcars.repository.CarsCategoryRepository;
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
    private final CarsCategoryRepository carsCategoryRepository;
    private final ConversionService conversionService;

    @Autowired
    public CarService(CarRepository carRepository, ConversionService conversionService,
            CategoryRepository categoryRepository, CarsCategoryRepository carsCategoryRepository) {
        this.carRepository = carRepository;
        this.conversionService = conversionService;
        this.categoryRepository = categoryRepository;
        this.carsCategoryRepository = carsCategoryRepository;
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
        Cars savedCar = carRepository.save(transformed);

        // Crear y guardar una nueva entrada en cars_category para vincular el car con la categoría
        CarsCategory carsCategory = new CarsCategory();
        carsCategory.setCars(savedCar);
        carsCategory.setCategory(category);
        carsCategoryRepository.save(carsCategory);

        CarsDto resultDto = conversionService.convert(savedCar, CarsDto.class);

        resultDto.setCategory(conversionService.convert(category, CategoryDto.class));

        return resultDto;

    }

    public CarsDto updateCar(CarsDto carsDto, Long id) {
        Cars existingCar = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        Category newCategory = categoryRepository.findById(carsDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("New category not found"));

        BeanUtils.copyProperties(carsDto, existingCar, "id", "category");

        Category currentCategory = existingCar.getCategory();

        // Si la categoría ha cambiado, actualizar la relación en la tabla cars_category
        if (!Objects.equals(newCategory.getId(), currentCategory.getId())) {
            // Buscar la entrada en la tabla cars_category y eliminarla
            List<CarsCategory> carsCategories = carsCategoryRepository.findByCarsIdAndCategoryId(existingCar.getId(), currentCategory.getId());
            carsCategoryRepository.deleteAll(carsCategories);

            // Crear una nueva relación en la tabla cars_category con la nueva categoría
            CarsCategory newCarsCategory = new CarsCategory();
            newCarsCategory.setCars(existingCar);
            newCarsCategory.setCategory(newCategory);
            carsCategoryRepository.save(newCarsCategory);
        }

        existingCar.setCategory(newCategory);

        Cars result = carRepository.save(existingCar);
        CarsDto resultDto = conversionService.convert(result, CarsDto.class);
        resultDto.setCategory(conversionService.convert(newCategory, CategoryDto.class));

        return resultDto;
    }

    @Transactional
    public void deleteCar(Long id) {
        Cars existingCar = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        CarsCategory carsCategory = existingCar.getCarsCategory();
        if (carsCategory != null) {
            carsCategoryRepository.delete(carsCategory);
        }

        carRepository.delete(existingCar);
    }

}
