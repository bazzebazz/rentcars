package com.carsite.rentcars.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Cars extends Base {

    @Size(min = 1, max = 20)
    @NotBlank(message = "brand is required")
    @Column(name = "brand", nullable = false, length = 30)
    private String brand;

    @Size(min = 1, max = 20)
    @NotBlank(message = "model is required")
    @Column(name = "model", nullable = false, length = 20)
    private String model;

    @NotNull(message = "year is required")
    @Range(min = 2018, max = 2025, message = "Year must be between 2018 and 2025")
    @Column(name = "year", nullable = false)
    private Integer year;

    @NotNull(message = "available is required")
    @Column(name = "available", nullable = false)
    private Boolean available;

    @Size(min = 1, max = 100)
    @NotBlank(message = "description is required")
    @Column(name = "description", nullable = false, length = 100)
    private String description;

    // TODO: implements image for cars

    @Valid
    @NotNull(message = "Category is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @Valid
    @NotNull(message = "Price is required")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "price_id", nullable = false)
    private Price price;

    @OneToOne(mappedBy = "cars", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    private CarsCategory carsCategory;

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

    public CarsCategory getCarsCategory() {
        return carsCategory;
    }

    public void setCarsCategory(CarsCategory carsCategory) {
        this.carsCategory = carsCategory;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
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
                && Objects.equals(model, cars.model) && Objects.equals(year, cars.year)
                && Objects.equals(available, cars.available) && Objects.equals(description, cars.description)
                && Objects.equals(price, cars.price) && Objects.equals(category, cars.category)
                && Objects.equals(carsCategory, cars.carsCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), brand, model, year, available, description, price, category,
                carsCategory);
    }

    @Override
    public String toString() {
        return "Cars{" + "id=" + getId() + ", brand='" + brand + '\'' + ", model='" + model + '\'' + ", year=" + year
                + ", available=" + available + ", description='" + description + '\'' + ", price=" + price
                + ", category=" + category + ", carsCategory=" + carsCategory + ", version=" + version + '}';
    }
}
