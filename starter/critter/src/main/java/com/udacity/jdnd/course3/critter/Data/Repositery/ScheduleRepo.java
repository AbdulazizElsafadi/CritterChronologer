package com.udacity.jdnd.course3.critter.Data.Repositery;

import com.udacity.jdnd.course3.critter.Data.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepo extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByPets_Id(Long petId);

    List<Schedule> findAllByEmployees_Id(Long employeeId);

}
