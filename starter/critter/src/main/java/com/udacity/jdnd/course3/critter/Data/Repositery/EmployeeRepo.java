package com.udacity.jdnd.course3.critter.Data.Repositery;


import com.udacity.jdnd.course3.critter.Data.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    List<Employee> findAllByDaysAvailableContaining(DayOfWeek dayOfWeek);

}
