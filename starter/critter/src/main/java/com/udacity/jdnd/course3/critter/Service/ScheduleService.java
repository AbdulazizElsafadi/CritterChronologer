package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Data.Entity.Pet;
import com.udacity.jdnd.course3.critter.Data.Entity.Schedule;
import com.udacity.jdnd.course3.critter.Data.Repositery.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepo scheduleRepo;

    @Autowired
    private PetService petService;

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepo.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepo.findAll();
    }

    public Schedule getScheduleById(Long id) {
        return scheduleRepo.findById(id).get();
    }

    public List<Schedule> getSchedulesByPetId(Long petId) {
        return scheduleRepo.findAllByPets_Id(petId);
    }

    public List<Schedule> getSchedulesByEmployeeId(Long employeeId) {
        return scheduleRepo.findAllByEmployees_Id(employeeId);
    }

    public List<Schedule> getSchedulesByCustomerId(Long customerId) {

        // find owner pets
        List<Long> petIds = petService.getPetsByOwnerId(customerId).stream()
                .map(Pet::getId).collect(Collectors.toList());

        // find schedules for each pet
        List<Schedule> schedules = new ArrayList<>();
        for(Long petId : petIds) {
            schedules.addAll(scheduleRepo.findAllByPets_Id(petId));
        }
        return schedules;
    }
}
