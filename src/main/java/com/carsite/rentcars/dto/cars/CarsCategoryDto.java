package com.carsite.rentcars.dto.cars;

import com.carsite.rentcars.models.Cars;
import com.carsite.rentcars.models.Category;

public class CarsCategoryDto {

    private Long id;
    Long version;
    private Cars cars;
    private Category category;

    public Cars getCars() {
        return cars;
    }

    public void setCars(Cars cars) {
        this.cars = cars;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
