package com.udacity.jdnd.course3.critter.Data.Repositery;

import com.udacity.jdnd.course3.critter.Data.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

  Customer findByPetsId(Long petId);
}
