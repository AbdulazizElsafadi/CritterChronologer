package com.udacity.jdnd.course3.critter.Controller;

import com.udacity.jdnd.course3.critter.Controller.DTO.User.Customer.CustomerDTO;
import com.udacity.jdnd.course3.critter.Controller.DTO.User.Employee.EmployeeDTO;
import com.udacity.jdnd.course3.critter.Controller.DTO.User.Employee.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.Data.Entity.Customer;
import com.udacity.jdnd.course3.critter.Data.Entity.Employee;
import com.udacity.jdnd.course3.critter.Data.Entity.Pet;
import com.udacity.jdnd.course3.critter.Service.PetService;
import com.udacity.jdnd.course3.critter.Service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 * <p>
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PetService petService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
//        System.out.println("customerDTO in controller:" + customerDTO);
        Customer customer = converterCustomerDTOToCustomer(customerDTO);

        // setting customers' pets
        // It's only used once, so I didn't include it in the converter method.
        if (customerDTO.getPetIds() != null) {
            List<Pet> pets = new ArrayList<>();
            List<Long> petIds = customerDTO.getPetIds();
            for (int i = 0; i < petIds.size(); i++) {
                Pet pet = petService.getPetById(petIds.get(i));
                pets.add(pet);
                pet.setOwner(customer);
            }
            customer.setPets(pets);
        }

        return converterCustomerToCustomerDTO(userService.saveCustomer(customer));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        return convertListOfCustomersToDTO(userService.getAllCustomers());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        return converterCustomerToCustomerDTO(userService.getCustomerByPetId(petId));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
//        System.out.println("employeeDTO in controller:" + employeeDTO);
        Employee newEmployee = userService.saveEmployee(converterEmployeeDTOToEmployee(employeeDTO));
//        System.out.println("Saved employee in controller:" + newEmployee);
        return converterEmployeeToEmployeeDTO(newEmployee);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return converterEmployeeToEmployeeDTO(userService.getEmployeeById(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        userService.setEmployeeAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> employees = userService
                .getEmployeesForService(employeeDTO.getSkills(), employeeDTO.getDate().getDayOfWeek());
        return convertListOfEmployeesToDTO(employees);
    }

    /* Customer Converters */
    private static CustomerDTO converterCustomerToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        if (customer.getPets() != null) {
            customerDTO.setPetIds(customer.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        }
        return customerDTO;
    }

    private static Customer converterCustomerDTOToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    private static List<CustomerDTO> convertListOfCustomersToDTO(List<Customer> customers) {
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for (Customer customer : customers) {
            CustomerDTO customerDTO = converterCustomerToCustomerDTO(customer);
            customerDTOList.add(customerDTO);
        }

        return customerDTOList;
    }

    private static List<Customer> convertListOfDTOToCustomers(List<CustomerDTO> customerDTOList) {
        List<Customer> customers = new ArrayList<>();

        for (CustomerDTO customerDTO : customerDTOList) {
            Customer customer = converterCustomerDTOToCustomer(customerDTO);
            customers.add(customer);
        }

        return customers;
    }

    /* Employee Converters */
    private static EmployeeDTO converterEmployeeToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    private static Employee converterEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

    private static List<EmployeeDTO> convertListOfEmployeesToDTO(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();

        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = converterEmployeeToEmployeeDTO(employee);
            employeeDTOList.add(employeeDTO);
        }

        return employeeDTOList;
    }

    private static List<Employee> convertListOfDTOToEmployees(List<EmployeeDTO> employeeDTOList) {
        List<Employee> employees = new ArrayList<>();

        for (EmployeeDTO employeeDTO : employeeDTOList) {
            Employee employee = converterEmployeeDTOToEmployee(employeeDTO);
            employees.add(employee);
        }

        return employees;
    }


}
