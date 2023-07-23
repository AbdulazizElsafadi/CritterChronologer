package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Controller.DTO.User.Employee.EmployeeSkill;
import com.udacity.jdnd.course3.critter.Data.Entity.Employee;
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
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

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
