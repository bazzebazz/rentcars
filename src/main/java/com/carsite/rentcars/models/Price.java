package com.carsite.rentcars.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Price extends Base {
    @Column(name = "daily_price", nullable = false)
    private BigDecimal dailyPrice;

    @Column(name = "discount_rate", nullable = false)
    private BigDecimal discountRate;

    @Column(name = "is_in_discount", nullable = false)
    private Boolean isInDiscount;

    @Column(name = "total_price", nullable = true)
    private BigDecimal totalPrice;

    @OneToOne(mappedBy = "price")
    private Cars cars;

    public BigDecimal getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(BigDecimal dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public Boolean getInDiscount() {
        return isInDiscount;
    }

    public void setInDiscount(Boolean inDiscount) {
        isInDiscount = inDiscount;
    }

    public Cars getCars() {
        return cars;
    }

    public void setCars(Cars cars) {
        this.cars = cars;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Price price = (Price) o;
        return Objects.equals(getId(), price.getId()) && Objects.equals(dailyPrice, price.dailyPrice)
                && Objects.equals(discountRate, price.discountRate) && Objects.equals(isInDiscount, price.isInDiscount)
                && Objects.equals(totalPrice, price.totalPrice) && Objects.equals(cars, price.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), dailyPrice, discountRate, isInDiscount, totalPrice, cars);
    }

    @Override
    public String toString() {
        return "Price{" + "id=" + getId() + ", dailyPrice=" + dailyPrice + ", discountRate=" + discountRate
                + ", isInDiscount=" + isInDiscount + ", totalPrice=" + totalPrice + ", cars=" + cars + ", version="
                + version + '}';
    }
}
