package com.pet.services.servicesImpl;

import com.pet.entitys.UserRoles;
import com.pet.repository.UserRolRepo;
import com.pet.services.servicesIa.IRolesUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
@Service
public class UserRolesService implements IRolesUser {

    @Autowired
    private UserRolRepo userRolRepo;


    @Override
    public UserRoles save(UserRoles userRoles) {

        return userRolRepo.save(userRoles);

    }
}
