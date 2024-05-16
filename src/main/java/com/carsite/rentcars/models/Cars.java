package com.carsite.rentcars.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Cars extends Base {

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "available", nullable = false)
    private Boolean available;

    @Column(name = "description", nullable = false)
    private String description;

    // TODO: implements image for cars

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!super.equals(o))
            return false;
        Cars cars = (Cars) o;
        return Objects.equals(getId(), cars.getId()) && Objects.equals(brand, cars.brand)
                && Objects.equals(model, cars.model) && Objects.equals(price, cars.price)
                && Objects.equals(year, cars.year) && Objects.equals(available, cars.available)
                && Objects.equals(description, cars.description) && Objects.equals(category, cars.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), brand, model, price, year, available, description, category);
    }

    @Override
    public String toString() {
        return "Cars{" + "id=" + getId() + ", brand='" + brand + '\'' + ", model='" + model + '\'' + ", price=" + price
                + ", year=" + year + ", available=" + available + ", description='" + description + '\'' + ", category="
                + category + '}';
    }
}
