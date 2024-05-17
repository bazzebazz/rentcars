package com.carsite.rentcars.repository;

import com.carsite.rentcars.models.Cars;
import com.carsite.rentcars.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Cars, Long> {

    // String QUERY_FIND_CARS_BY_CATEGORY = "SELECT car FROM Cars car JOIN car.category category WHERE category.name =
    // :categoryName";
    // String QUERY_FIND_CARS_BY_CATEGORY_ID = "SELECT car FROM Cars car JOIN car.category category WHERE category.id =
    // :categoryId";
    // String QUERY_FIND_CATEGORY_CAR = "SELECT category FROM Cars car JOIN car.category category WHERE car.id =
    // :carId";
    //
    // List<Cars> findAll();
    //
    // // get cars by category
    // @Query(QUERY_FIND_CARS_BY_CATEGORY)
    // List<Cars> findByCategoryName(String categoryName);
    //
    // // get cars by category id
    // @Query(QUERY_FIND_CARS_BY_CATEGORY_ID)
    // List<Cars> findByCategoryId(Long categoryId);
    //
    // // get category car
    // @Query(QUERY_FIND_CATEGORY_CAR)
    // Category findCategoryByCarId(Long carId);

}
