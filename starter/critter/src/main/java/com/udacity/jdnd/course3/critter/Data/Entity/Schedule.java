package com.udacity.jdnd.course3.critter.Data.Entity;

import com.udacity.jdnd.course3.critter.Controller.DTO.User.Employee.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private long id;

    @ManyToMany
    @JoinTable(
            name = "schedule_employee",
            joinColumns = {@JoinColumn(name = "schedule_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")}
    )
    private List<Employee> employees;


    @ManyToMany
    @JoinTable(
            name = "schedule_pet",
            joinColumns = {@JoinColumn(name = "schedule_id")},
            inverseJoinColumns = {@JoinColumn(name = "pet_id")}
    )
    private List<Pet> pets;

    private LocalDate date;

    @ElementCollection(targetClass = EmployeeSkill.class)
    private Set<EmployeeSkill> activities;

    public Schedule() {
    }

    public Schedule(long id, List<Pet> pets, List<Employee> employees, LocalDate date, Set<EmployeeSkill> activities) {
        this.id = id;
        this.employees = employees;
        this.pets = pets;
        this.date = date;
        this.activities = activities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", employees=" + employees +
                ", pets=" + pets +
                ", date=" + date +
                ", activities=" + activities +
                '}';
    }
}

