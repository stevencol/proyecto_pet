package com.pet.controllers;

import com.pet.entitys.Role;
import com.pet.services.servicesIa.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
@CrossOrigin("*")
public class RolController {

    @Autowired
    private IRolService roleService;
    @PostMapping
    public void saveRol(@RequestBody Role role){

        System.out.println(roleService.save(role));

    }
}
