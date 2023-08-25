package com.pet.services.servicesImpl;

import com.pet.entitys.Raza;
import com.pet.repository.RazaRepo;
import com.pet.services.servicesIa.RazaServiceIa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RazaServiceImpl implements RazaServiceIa {

        @Autowired
        private RazaRepo respoRaza;

    @Override
    public Raza save(Raza raza) {
        System.out.println(raza.getName());
        return respoRaza.save(raza);

    }

    @Override
    public List<Raza> findAll() {
        return respoRaza.findAll();
    }

    @Override
    public void delte(Long id) {
     respoRaza.deleteById(id);
    }

    @Override
    public Raza findById(Long id) {
        return  respoRaza.findById(id).orElse(null);
    }
}
