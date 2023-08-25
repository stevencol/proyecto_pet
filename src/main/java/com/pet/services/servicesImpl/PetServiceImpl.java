package com.pet.services.servicesImpl;

import com.pet.entitys.Pet;
import com.pet.repository.PetRepo;
import com.pet.services.servicesIa.PetServiceIa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetServiceIa {

    @Autowired
    PetRepo petRepo;

    @Autowired
    CloudinariServiceImpl cloudinariService;
    @Override
    public Pet save(Pet pet) {


        return petRepo.save(pet);


    }

    @Override
    public List<Pet> findAll() {
       return petRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {


        petRepo.deleteById(id);
        cloudinariService.deleteFloder(id);


    }

    @Override
    public Pet findById(Long id) {
        return  petRepo.findById(id).orElse(null);
    }

}
