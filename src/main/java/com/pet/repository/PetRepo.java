package com.pet.repository;

import com.pet.entitys.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PetRepo extends JpaRepository<Pet, Long> {
}
