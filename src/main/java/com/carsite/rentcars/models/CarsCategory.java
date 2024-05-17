package com.carsite.rentcars.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Objects;

@Entity
public class CarsCategory extends Base {

    @ManyToOne
    @JoinColumn(name = "cars_id")
    private Cars cars;

    @ManyToOne
    @JoinColumn(name = "category_id")
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CarsCategory that = (CarsCategory) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(cars, that.cars)
                && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), cars, category);
    }

    @Override
    public String toString() {
        return "CarsCategory{" + "id=" + getId() + ", cars=" + cars + ", category=" + category + '}';
    }
}
