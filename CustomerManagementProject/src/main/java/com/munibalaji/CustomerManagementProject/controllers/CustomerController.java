package com.munibalaji.CustomerManagementProject.controllers;


import com.munibalaji.CustomerManagementProject.dtos.CustomerRequestDto;
import com.munibalaji.CustomerManagementProject.dtos.CustomerResponseDto;
import com.munibalaji.CustomerManagementProject.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Customer Management ", description = "Operations related to customers")
@RestController
@RequestMapping("api/v1/customers")
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


    @GetMapping("/search")
    public ResponseEntity<Page<CustomerResponseDto>> searchCustomers(@RequestParam(required = false) String name,
                                                                     @RequestParam(required = false) String email,
                                                                     @RequestParam(required = false) String address,
                                                                     @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "5") int size,
                                                                     @RequestParam(defaultValue = "id") String sortBy,
                                                                     @RequestParam(defaultValue = "desc")String direction){
        return ResponseEntity.ok(
                customerService.searchCustomers(name, email, address, page, size, sortBy, direction));
    }

}
