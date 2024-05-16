package com.carsite.rentcars.models;

import jakarta.persistence.Entity;

@Entity
public class Client extends Base {

    private String name;
    private String lastName;
    private String password;
    private String email;
    private Integer phone;
    private String driverLicense;
    private String driverLicenseCategory;
    private String driverLicenseExpiration;
    private String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getDriverLicenseCategory() {
        return driverLicenseCategory;
    }

    public void setDriverLicenseCategory(String driverLicenseCategory) {
        this.driverLicenseCategory = driverLicenseCategory;
    }

    public String getDriverLicenseExpiration() {
        return driverLicenseExpiration;
    }

    public void setDriverLicenseExpiration(String driverLicenseExpiration) {
        this.driverLicenseExpiration = driverLicenseExpiration;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
