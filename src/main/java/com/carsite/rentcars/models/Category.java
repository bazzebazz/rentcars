package com.carsite.rentcars.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Category extends Base {

    private String name;
    private String description;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Category category = (Category) o;
        return Objects.equals(getId(), category.getId()) && Objects.equals(name, category.name)
                && Objects.equals(description, category.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name, description);
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + getId() + ", name='" + name + '\'' + ", description='" + description + '\'' + '}';
    }
}
