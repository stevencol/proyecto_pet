package com.pet.repository;

import com.pet.entitys.Applications;
import com.pet.enums.ApplicationStatus;
import com.pet.enums.Sexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationsRepo  extends JpaRepository<Applications, Long> {


    @Query("select  a from  Applications a where  a.person.id=:id")
    public List<Applications> findByIdPerson(@Param("id") Long id);


    @Query("SELECT   COUNT(a) from Applications  a where  a.status=:#{T(com.pet.enums.ApplicationStatus).PENDING} AND a.person.id=:id")
    public int finCounByStatus(@Param("id") Long id);


    @Query("SELECT   a from Applications  a where  a.status=:status")
    public List<Applications> findAllbyStatus(@Param("status") ApplicationStatus status);


    @Query("SELECT   a from Applications  a where  a.status=:status and a.person.id=:id")
    public List<Applications> findAllbyStatusAndPersonId(@Param("id") Long id , @Param("status") ApplicationStatus status);







}
