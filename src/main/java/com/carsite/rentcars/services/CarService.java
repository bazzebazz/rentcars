package com.carsite.rentcars.services;

import com.carsite.rentcars.dto.cars.CarsDto;
import com.carsite.rentcars.dto.cars.CategoryDto;
import com.carsite.rentcars.enums.APIError;
import com.carsite.rentcars.exceptions.CarException;
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
        if(result.isEmpty()){
            throw new CarException(APIError.CAR_NOT_FOUND);
        }
        return conversionService.convert(result.get(), CarsDto.class);
    }

    public CarsDto saveCar(CarsDto carsDto) {
        if(Objects.nonNull(carsDto.getId())){
            throw new CarException(APIError.CAR_WITH_SAME_ID);
        }
        Category category = categoryRepository.findById(carsDto.getCategoryId())
                .orElseThrow(() -> new CarException(APIError.CATEGORY_NOT_FOUND));

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
        Cars existingCar = carRepository.findById(id).orElseThrow(() -> new CarException(APIError.CAR_NOT_FOUND));
        Category category = categoryRepository.findById(carsDto.getCategoryId())
                .orElseThrow(() -> new CarException(APIError.CATEGORY_NOT_FOUND));

        // Comprobar si la relación ya existe
        if (carsCategoryRepository.existsByCarsIdAndCategoryId(existingCar.getId(), category.getId())) {
            throw new CarException(APIError.CAR_CATEGORY_ALREADY_EXIST);
        }
        // Primero, eliminar todas las relaciones anteriores en la tabla intermedia cars_category
        carsCategoryRepository.deleteByCarsId(existingCar.getId());

        // Actualizar los campos del Car existente con los valores del DTO
        existingCar.setBrand(carsDto.getBrand());
        existingCar.setModel(carsDto.getModel());
        existingCar.setPrice(carsDto.getPrice());
        existingCar.setYear(carsDto.getYear());
        existingCar.setAvailable(carsDto.getAvailable());
        existingCar.setDescription(carsDto.getDescription());

        // Añadir la nueva relación
        CarsCategory carsCategory = new CarsCategory();
        carsCategory.setCars(existingCar);
        carsCategory.setCategory(category);
        carsCategoryRepository.save(carsCategory);

        // Guardar el coche actualizado
        Cars updatedCar = carRepository.save(existingCar);

        // Convertir la entidad actualizada a DTO y devolverla
        CarsDto resultDto = conversionService.convert(updatedCar, CarsDto.class);
        resultDto.setCategory(conversionService.convert(category, CategoryDto.class));

        return resultDto;
    }

    @Transactional
    public void deleteCar(Long id) {
        Cars existingCar = carRepository.findById(id).orElseThrow(() -> new CarException(APIError.CAR_NOT_FOUND));
        // Eliminar todas las relaciones en la tabla intermedia cars_category
        carsCategoryRepository.deleteByCarsId(existingCar.getId());

        carRepository.delete(existingCar);
    }

}
