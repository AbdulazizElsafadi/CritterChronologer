package com.udacity.jdnd.course3.critter.Data.Entity;

import com.udacity.jdnd.course3.critter.Controller.DTO.Pet.PetType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private PetType type;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer owner;
    private LocalDate birthDate;
    private String notes;

    public Pet() {
    }

    public Pet(long id, PetType type, String name, Customer owner, LocalDate birthDate, String notes) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.owner = owner;
        this.birthDate = birthDate;
        this.notes = notes;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", birthDate=" + birthDate +
                ", notes='" + notes + '\'' +
                '}';
    }
}

