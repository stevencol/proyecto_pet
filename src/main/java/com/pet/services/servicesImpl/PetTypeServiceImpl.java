package com.pet.services.servicesImpl;

import com.pet.entitys.PetType;
import com.pet.repository.TypeRepo;
import com.pet.services.servicesIa.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetTypeServiceImpl  implements PetTypeService {

    @Autowired
    TypeRepo typeRepo;
    @Override
    public PetType findById(Long id) {
        return  typeRepo.findById(id).orElse(null) ;
    }

    @Override
    public List<PetType>getAll() {
        Optional<List<PetType>> lista =  Optional.ofNullable(typeRepo.findAll());
        return  lista.isPresent()? typeRepo.findAll(): null;
    }

    @Override
    public void save(PetType petType) {

        if(petType!=null){
            typeRepo.save(petType);
        }
    }

    @Override
    public void delete(Long id) {
           PetType type = typeRepo.findById(id).orElse(null);
        if(type!=null){
            typeRepo.deleteById(id);
        }
    }
}
