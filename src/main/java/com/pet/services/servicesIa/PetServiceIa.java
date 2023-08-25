package com.pet.services.servicesIa;

import com.pet.entitys.Pet;

import java.util.List;

public interface PetServiceIa {


    Pet save(Pet pet);

    List<Pet> findAll();

    void deleteById(Long id);
    Pet findById(Long id);


}
