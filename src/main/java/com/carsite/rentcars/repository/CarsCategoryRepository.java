package com.carsite.rentcars.repository;

import com.carsite.rentcars.models.CarsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CarsCategoryRepository extends JpaRepository<CarsCategory, Long> {
    List<CarsCategory> findByCarsIdAndCategoryId(Long carsId, Long categoryId);

    @Transactional
    void deleteById(Long id);
}
