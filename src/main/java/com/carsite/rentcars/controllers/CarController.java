package com.carsite.rentcars.controllers;

import com.carsite.rentcars.dto.cars.CarsDto;
import com.carsite.rentcars.dto.cars.CategoryDto;
import com.carsite.rentcars.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarService carService;

    // Getting All Cars
    @GetMapping
    public ResponseEntity<List<CarsDto>> getAllCars() {
        List<CarsDto> response = carService.getAllCars();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Getting Car ById
    @GetMapping("/{id}")
    public ResponseEntity<CarsDto> getCarById(@PathVariable Long id) {
        CarsDto response = carService.getCarById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Saving a new car for rental
    @PostMapping
    public ResponseEntity<CarsDto> savingCar(@RequestBody CarsDto carsDto) {
        CarsDto response = carService.saveCar(carsDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Updating a car
    @PutMapping("/{id}")
    public ResponseEntity<CarsDto> updateCar(@RequestBody CarsDto carsDto, @PathVariable Long id) {
        CarsDto response = carService.updateCar(carsDto, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Deleting a car
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
