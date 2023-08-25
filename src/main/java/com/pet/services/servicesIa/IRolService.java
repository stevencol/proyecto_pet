package com.pet.services.servicesIa;


import com.pet.entitys.Role;
import com.pet.repository.RolRepo;

public interface IRolService {
    Role findById(Long id);
    Role save(Role rol);
}
