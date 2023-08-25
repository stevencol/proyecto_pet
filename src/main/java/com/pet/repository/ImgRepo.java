package com.pet.repository;

import com.pet.entitys.Imgs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ImgRepo extends JpaRepository<Imgs, Long> {



    @Query("select i FROM Imgs i where  i.idCloud=:idCloud")
    Imgs findByIdCloud(@Param("idCloud") String idCloud);

}
