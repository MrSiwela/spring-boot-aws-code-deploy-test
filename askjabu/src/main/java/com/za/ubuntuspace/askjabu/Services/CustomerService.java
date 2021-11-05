package com.za.ubuntuspace.askjabu.Services;

import com.za.ubuntuspace.askjabu.Entities.Customer;
import com.za.ubuntuspace.askjabu.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomerById(int id){
        return customerRepository.findById(id).get();
    }

}
