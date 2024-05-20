package com.carsite.rentcars.enums;

import org.springframework.http.HttpStatus;

public enum APIError {
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "The are attributes with wrong values"),
    BAD_FORMAT(HttpStatus.BAD_REQUEST, "The message not have a correct form"),
    CAR_NOT_FOUND(HttpStatus.NOT_FOUND, "Car not found"),
    CAR_WITH_SAME_ID(HttpStatus.BAD_REQUEST, "There is a car with the same id"),
    CAR_CATEGORY_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "Car category already exist"),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "Category not found"),
    CAR_PRICE_NOT_FOUND(HttpStatus.NOT_FOUND, "Price not found for this car");

    private final HttpStatus httpStatus;
    private final String message;

    APIError(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
