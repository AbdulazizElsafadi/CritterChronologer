package com.udacity.jdnd.course3.critter.Service;


import com.udacity.jdnd.course3.critter.Controller.DTO.User.Employee.EmployeeSkill;
import com.udacity.jdnd.course3.critter.Data.Entity.Customer;
import com.udacity.jdnd.course3.critter.Data.Entity.Employee;
import com.udacity.jdnd.course3.critter.Data.Repositery.CustomerRepo;
import com.udacity.jdnd.course3.critter.Data.Repositery.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

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

    /* Employee */
    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepo.findById(id).get();
    }

    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, Long employeeId) {
        Employee employee = employeeRepo.findById(employeeId).get();
        employee.setDaysAvailable(daysAvailable);
        employeeRepo.save(employee);
    }

    public List<Employee> getEmployeesForService(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek) {
        return employeeRepo.findAllByDaysAvailableContaining(dayOfWeek).stream()
                .filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
    }

}
