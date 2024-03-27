package com.mihaildanilov.dto;

public record NewCustomerRequest(
        String name,
        String surname,
        String email,
        Integer age

) {
}