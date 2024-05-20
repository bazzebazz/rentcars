package com.carsite.rentcars.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Category extends Base {

    private String name;
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Cars> cars;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Cars> getCars() {
        return cars;
    }

    public void setCars(List<Cars> cars) {
        this.cars = cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Category category = (Category) o;
        return Objects.equals(getId(), category.getId()) && Objects.equals(name, category.name)
                && Objects.equals(description, category.description) && Objects.equals(cars, category.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), name, description, cars);
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + getId() + ", name='" + name + '\'' + ", description='" + description + '\''
                + ", cars=" + cars + '}';
    }
}
