package com.za.ubuntuspace.askjabu.Repositories;

import com.za.ubuntuspace.askjabu.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
