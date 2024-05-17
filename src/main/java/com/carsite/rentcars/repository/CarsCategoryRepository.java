package com.carsite.rentcars.repository;

import com.carsite.rentcars.models.CarsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface CarsCategoryRepository extends JpaRepository<CarsCategory, Long> {

    @Transactional
    void deleteById(Long id);

    @Transactional
    void deleteByCarsId(Long carId);

    boolean existsByCarsIdAndCategoryId(Long carId, Long categoryId);
}
