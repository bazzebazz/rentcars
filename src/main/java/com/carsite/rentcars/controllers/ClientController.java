package com.carsite.rentcars.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class ClientController {

    // Getting All Users
    @GetMapping
    public String getAllUsers() {
        return "Endpoint Works: gettingAllUsers";
    }

    // Getting User ById
    @GetMapping("/{id}")
    public String getUserById() {
        return "Endpoint works: getting user By Id";
    }

    // Saving a new user for rental
    @PostMapping
    public String savingUser() {
        return "Endpoint works: saving user";
    }

    // Updating a user
    @PutMapping("/{id}")
    public String updateUser() {
        return "Endpoint works: updating a user";
    }

    // Deleting a user
    @DeleteMapping("/{id}")
    public String deleteUser() {
        return "Endpoint works: deleting a user";
    }

}
