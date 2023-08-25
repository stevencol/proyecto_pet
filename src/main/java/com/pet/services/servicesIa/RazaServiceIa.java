package com.pet.services.servicesIa;

import com.pet.entitys.Raza;

import java.util.List;

public interface RazaServiceIa {
    Raza save(Raza raza);

     List<Raza> findAll();

     void delte(Long id);
    Raza findById(Long id);


}
