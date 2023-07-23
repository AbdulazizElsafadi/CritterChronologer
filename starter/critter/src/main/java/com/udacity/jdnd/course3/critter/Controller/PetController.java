package com.udacity.jdnd.course3.critter.Controller;

import com.udacity.jdnd.course3.critter.Controller.DTO.Pet.PetDTO;
import com.udacity.jdnd.course3.critter.Data.Entity.Customer;
import com.udacity.jdnd.course3.critter.Data.Entity.Pet;
import com.udacity.jdnd.course3.critter.Service.PetService;
import com.udacity.jdnd.course3.critter.Service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private UserService userService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
//        System.out.println("petDTO in controller:" + petDTO);
        Pet pet = converterPetDTOToPet(petDTO);
        Customer owner = userService.getCustomerById(petDTO.getOwnerId());
        pet.setOwner(owner);
        owner.getPets().add(pet);
        return converterPetToPetDTO(petService.savePet(pet));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return converterPetToPetDTO(petService.getPetById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets() {
        return convertListOfPetsToPetsDTO(petService.getAllPets());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return convertListOfPetsToPetsDTO(petService.getPetsByOwnerId(ownerId));
    }

    private static PetDTO converterPetToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getOwner().getId());
        return petDTO;
    }

    private static Pet converterPetDTOToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }

    private static List<PetDTO> convertListOfPetsToPetsDTO(List<Pet> pets) {
        List<PetDTO> petDTOList = new ArrayList<>();

        for (Pet pet : pets) {
            PetDTO petDTO = converterPetToPetDTO(pet);
            petDTOList.add(petDTO);
        }

        return petDTOList;
    }

    private static List<Pet> convertListOfPetsDTOToPets(List<PetDTO> petDTOList) {
        List<Pet> pets = new ArrayList<>();

        for (PetDTO petDTO : petDTOList) {
            Pet pet = converterPetDTOToPet(petDTO);
            pets.add(pet);
        }

        return pets;
    }
}
