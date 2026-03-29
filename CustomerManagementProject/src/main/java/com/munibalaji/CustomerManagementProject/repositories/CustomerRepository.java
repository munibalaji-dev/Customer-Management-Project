package com.munibalaji.CustomerManagementProject.repositories;

import com.munibalaji.CustomerManagementProject.models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long>{

    boolean existsByEmail(String email);
    boolean existsByPhone(Long phone);
}
