package com.pet.repository;

import com.pet.entitys.Raza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RazaRepo extends JpaRepository<Raza, Long> {
}
