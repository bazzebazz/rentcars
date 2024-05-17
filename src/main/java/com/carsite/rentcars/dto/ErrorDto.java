package com.carsite.rentcars.dto;

import java.util.List;

public class ErrorDto {
    private String description;
    private List<String> reasons;

    public ErrorDto(String description, List<String> reasons) {
        this.description = description;
        this.reasons = reasons;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getReasons() {
        return reasons;
    }
}
