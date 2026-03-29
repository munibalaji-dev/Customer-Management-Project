package com.munibalaji.CustomerManagementProject.controllers;


import com.munibalaji.CustomerManagementProject.dtos.CustomerRequestDto;
import com.munibalaji.CustomerManagementProject.dtos.CustomerResponseDto;
import com.munibalaji.CustomerManagementProject.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Customer Management APIs", description = "Operations related to customer management")
@RestController
@RequestMapping("api/v2/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }



    @Operation(summary = "To create customer")
    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto){
        return new ResponseEntity<>(customerService.createCustomer(customerRequestDto), HttpStatus.CREATED);
    }




    @Operation(summary = "To get the customer using their id")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable Long id){
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }




    @Operation(summary = "To get all customers")
    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers(){
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }




    @Operation(summary = "To update customer based on their id")
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> updateCustomerById(@PathVariable Long id, @RequestBody CustomerRequestDto customerRequestDto){
        return new ResponseEntity<>(customerService.updateCustomerById(id, customerRequestDto), HttpStatus.OK);
    }




    @Operation(summary = "To delete the customer using their id")
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> deleteCustomerById(@PathVariable Long id){
        return new ResponseEntity<>(customerService.deleteCustomerById(id), HttpStatus.OK);
    }

}
