package com.pet.repository;

import com.pet.entitys.Imgs;
import com.pet.entitys.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepo extends JpaRepository<Person , Long> {
    @Query("select p FROM Person p where  p.password=:password  and  p.email=:email")
    Person finByUserAndPassword(@Param("password") String password,@Param("email") String email);

@Query("SELECT   COUNT(p) from Person  p where  p.email=:email")
    int findRegister(@Param("email") String email);


    @Query("SELECT   p from Person  p where  p.email=:email")
    Person findByEmail(@Param("email") String email);

}
