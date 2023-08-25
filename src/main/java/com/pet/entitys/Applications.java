package com.pet.entitys;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.Data;


@Entity
 @Table(name = "applications")
 @Data
public class Applications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;


     @ManyToOne(fetch = FetchType.LAZY/*,cascade = {CascadeType.PERSIST , CascadeType.MERGE}*/)
     @JoinColumn(name = "person_id")
     private Person person;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "pet_id")
     private Pet pet;

     @Column(name = "status")
     private ApplicationStatus status;

     @Column(name = "document")
     private  String document;

     @Column(name = "alternativePhone")
     private String alternativePhone;

     @Column(name = "address")
     private String address;

     @Column(name = "adoptionMotivation")
      private  String adoptionMotivation;




}
