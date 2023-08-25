package com.pet.services.servicesImpl;

import com.pet.entitys.Role;
import com.pet.repository.RolRepo;
import com.pet.services.servicesIa.IRolService;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService implements IRolService {
    @Autowired
    private  RolRepo repo;

    @Override
    public Role findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Role save(Role rol) {
        return  repo.save(rol);
    }


}
