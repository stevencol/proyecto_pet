package com.pet.services.servicesIa;

import com.pet.entitys.PetType;

import java.util.List;

public interface PetTypeService {
    PetType findById(Long id);

    List<PetType> getAll();


    void save(PetType petType);
    void delete(Long id);
}
