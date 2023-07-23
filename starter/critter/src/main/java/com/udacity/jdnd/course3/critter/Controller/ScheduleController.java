package com.udacity.jdnd.course3.critter.Controller;

import com.udacity.jdnd.course3.critter.Controller.DTO.Schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.Data.Entity.Employee;
import com.udacity.jdnd.course3.critter.Data.Entity.Pet;
import com.udacity.jdnd.course3.critter.Data.Entity.Schedule;
import com.udacity.jdnd.course3.critter.Service.EmployeeService;
import com.udacity.jdnd.course3.critter.Service.PetService;
import com.udacity.jdnd.course3.critter.Service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
//        System.out.println("ScheduleDTO: " + scheduleDTO);
        return converterScheduleToScheduleDTO(scheduleService.saveSchedule(converterScheduleDTOToSchedule(scheduleDTO)));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return convertListOfSchedulesToDTO(scheduleService.getAllSchedules());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return convertListOfSchedulesToDTO(scheduleService.getSchedulesByPetId(petId));
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return convertListOfSchedulesToDTO(scheduleService.getSchedulesByEmployeeId(employeeId));
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return convertListOfSchedulesToDTO(scheduleService.getSchedulesByCustomerId(customerId));
    }

    /* Schedule converters */
    private ScheduleDTO converterScheduleToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO, "employeeIds", "petIds");

        // set employee ids
        scheduleDTO.setEmployeeIds(schedule.getEmployees().stream()
                .map(Employee::getId).collect(Collectors.toList()));

        // set pet ids
        scheduleDTO.setPetIds(schedule.getPets().stream()
                .map(Pet::getId).collect(Collectors.toList()));

//        System.out.println("ScheduleDTO in convert to DTO: " + scheduleDTO);

        return scheduleDTO;
    }

    private Schedule converterScheduleDTOToSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();

        BeanUtils.copyProperties(scheduleDTO, schedule, "employees", "pets");

        // set employees
        List<Employee> employees = new ArrayList<>();
        if (scheduleDTO.getEmployeeIds() != null) {
            for (Long employeeId : scheduleDTO.getEmployeeIds()) {
                Employee employee = employeeService.getEmployeeById(employeeId);
                employees.add(employee);
            }
        }

        List<Pet> pets = new ArrayList<>();
        if (scheduleDTO.getPetIds() != null) {
            for (Long petId : scheduleDTO.getPetIds()) {
                Pet pet = petService.getPetById(petId);
                pets.add(pet);
            }
        }

        schedule.setEmployees(employees);
        schedule.setPets(pets);

        return schedule;
    }

    private List<ScheduleDTO> convertListOfSchedulesToDTO(List<Schedule> schedules) {
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

        for (Schedule schedule : schedules) {
            ScheduleDTO scheduleDTO = converterScheduleToScheduleDTO(schedule);
            scheduleDTOList.add(scheduleDTO);
        }

        return scheduleDTOList;
    }

    private List<Schedule> convertListOfDTOToSchedules(List<ScheduleDTO> scheduleDTOList) {
        List<Schedule> schedules = new ArrayList<>();

        for (ScheduleDTO scheduleDTO : scheduleDTOList) {
            Schedule schedule = converterScheduleDTOToSchedule(scheduleDTO);
            schedules.add(schedule);
        }

        return schedules;
    }


}
