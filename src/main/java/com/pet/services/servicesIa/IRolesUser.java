package com.pet.services.servicesIa;

import com.pet.entitys.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface IRolesUser  {


    UserRoles save(UserRoles userRoles);
}
