package com.munibalaji.CustomerManagementProject.services;

import com.munibalaji.CustomerManagementProject.dtos.CustomerRequestDto;
import com.munibalaji.CustomerManagementProject.dtos.CustomerResponseDto;
import com.munibalaji.CustomerManagementProject.exceptions.ResourceNotFoundException;
import com.munibalaji.CustomerManagementProject.mappers.CustomerMapper;
import com.munibalaji.CustomerManagementProject.models.Customers;
import com.munibalaji.CustomerManagementProject.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImplementation implements CustomerService{

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImplementation(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


    @Override
    @Transactional
    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {

        // Here am checking the validation checks, it allows the data to save only if checks will pass
        validationsCheckingForUniqueness(customerRequestDto);

        Customers saved = CustomerMapper.customerRequestDtoToEntity(customerRequestDto);
        Customers customers = customerRepository.save(saved);

        return CustomerMapper.entityToCustomerResponseDto(customers);
    }

    @Override
    public CustomerResponseDto getCustomerById(Long id) {

        Customers getById = customerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("customer not found with your id check it once and try again"));

        return CustomerMapper.entityToCustomerResponseDto(getById);
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {

        List<Customers> getAll = customerRepository.findAll();

        return getAll.stream()
                .map(CustomerMapper::entityToCustomerResponseDto)
                .toList();
    }

    @Override
    public CustomerResponseDto updateCustomerById(Long id, CustomerRequestDto customerRequestDto) {

        Customers customers = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found with your id try again"));

        customers.setCustomerName(customerRequestDto.getCustomerName());
        customers.setEmail(customerRequestDto.getEmail());
        customers.setPhone(customerRequestDto.getPhone());
        customers.setAddress(customerRequestDto.getAddress());

        Customers updatedCustomer = customerRepository.save(customers);

        return CustomerMapper.entityToCustomerResponseDto(updatedCustomer);
    }

    @Override
    public CustomerResponseDto deleteCustomerById(Long id) {

        Customers customers = customerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("customer not found to delete with your id try again"));
        customerRepository.delete(customers);

        return null;
    }


    private void validationsCheckingForUniqueness(CustomerRequestDto customerRequestDto){
        if (customerRepository.existsByEmail(customerRequestDto.getEmail())){
            throw new ResourceNotFoundException("Email "+ customerRequestDto.getEmail()+ " is already exists");
        }

        if(customerRepository.existsByPhone(customerRequestDto.getPhone())){
            throw new ResourceNotFoundException("Phone number "+ customerRequestDto.getPhone()+ " is already in use");
        }
    }
}
