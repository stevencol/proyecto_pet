package com.pet.repository;

import com.pet.entitys.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolRepo extends JpaRepository<UserRoles , Long> {
}
