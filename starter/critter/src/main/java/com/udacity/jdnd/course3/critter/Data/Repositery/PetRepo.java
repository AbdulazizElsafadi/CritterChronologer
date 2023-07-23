package com.udacity.jdnd.course3.critter.Data.Repositery;

import com.udacity.jdnd.course3.critter.Data.Entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepo extends JpaRepository<Pet, Long> {

    List<Pet> findAllByOwnerId(Long ownerId);
}
