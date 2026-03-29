package com.munibalaji.CustomerManagementProject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Customers extends BaseModel{

    @NotBlank(message = "Customer name cannot be null")
    private String customerName;


    @Column(unique = true, nullable = false)
    @Email(message = "Provide a valid email")
    @NotBlank(message = "Email is required")
    private String email;


    @Column(unique = true, nullable = false)
    @NotNull(message = "Phone number is required")
    @Min(value = 1000000000L, message = "phone number must be 10 digits")
    @Max(value = 9999999999L, message = "phone number must be 10 digits")
    private Long phone;


    private String address;
}
