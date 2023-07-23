package com.udacity.jdnd.course3.critter.Data.Entity;

import com.udacity.jdnd.course3.critter.Controller.DTO.User.Employee.EmployeeSkill;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
public class Employee extends User{

    @ElementCollection(targetClass = EmployeeSkill.class)
    private Set<EmployeeSkill> skills;

    @ElementCollection(targetClass = DayOfWeek.class)
    private Set<DayOfWeek> daysAvailable;

    public Employee() {
    }

    public Employee(Long id, String name, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {
        super(id, name);
        this.skills = skills;
        this.daysAvailable = daysAvailable;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                "skills=" + skills +
                ", daysAvailable=" + daysAvailable +
                '}';
    }
}
