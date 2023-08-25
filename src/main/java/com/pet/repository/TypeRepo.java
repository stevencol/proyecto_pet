package com.pet.repository;

import com.pet.entitys.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepo  extends JpaRepository<PetType , Long> {



}
