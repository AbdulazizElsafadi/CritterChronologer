package com.udacity.jdnd.course3.critter.Service;


import com.udacity.jdnd.course3.critter.Data.Entity.Customer;
import com.udacity.jdnd.course3.critter.Data.Repositery.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    PetService petService;

    /* Customer */
    public Customer saveCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepo.findById(id).get();
    }

    public Customer getCustomerByPetId(Long petId) {
        return customerRepo.findByPetsId(petId);
    }
}
