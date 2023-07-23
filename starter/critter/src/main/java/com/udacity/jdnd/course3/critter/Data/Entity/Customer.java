package com.udacity.jdnd.course3.critter.Data.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends User {

    private String phoneNumber;
    private String notes;
    // mapped by owner
    @OneToMany(fetch = FetchType.EAGER)
    private List<Pet> pets = new ArrayList<>();

    public Customer() {
    }

    public Customer(Long id, String name, String phoneNumber, String notes, List<Pet> pets) {
        super(id, name);
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        this.pets = pets;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                "phoneNumber='" + phoneNumber + '\'' +
                ", notes='" + notes + '\'' +
                ", pets=" + pets +
                '}';
    }
}

