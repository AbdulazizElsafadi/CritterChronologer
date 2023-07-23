package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Data.Entity.Pet;
import com.udacity.jdnd.course3.critter.Data.Repositery.PetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    private PetRepo petRepo;

    public List<Pet> getAllPets() {
        return petRepo.findAll();
    }

    public Pet savePet(Pet pet) {
        return petRepo.save(pet);
    }

    public Pet getPetById(Long id) {
        return petRepo.findById(id).get();
    }

    public List<Pet> getPetsByOwnerId(Long ownerId) {
        return petRepo.findAllByOwnerId(ownerId);
    }
}
