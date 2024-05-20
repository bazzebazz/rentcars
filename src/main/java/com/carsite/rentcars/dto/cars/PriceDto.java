package com.carsite.rentcars.dto.cars;

import java.math.BigDecimal;

public class PriceDto {

    private Long id;
    Long version;
    private BigDecimal dailyPrice;
    private BigDecimal discountRate;
    private Boolean isInDiscount;
    private BigDecimal totalPrice;

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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
