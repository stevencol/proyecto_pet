package com.pet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet.enums.Roles;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="person")
@Data
public class Person {


        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;



        @Column(name = "firtsName")
        private String firtsName;


        @Column(name="secondName")
        private String secondName;


        @Column(name = "lastName")
        private String lastName;


        @Column(name = "email")
        private String email;



        @Column(name = "phone")
        private String phone;

        @Column(name = "password")
        private String password;



        @OneToMany(mappedBy = "person")
        @JsonProperty("applications")
        private List<Applications> applications = new ArrayList<>();

        @JsonIgnore
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL)
        List<UserRoles> roles;

  /*  @OneToOne(mappedBy = "person" , cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonProperty("img")
    private Imgs persona;
*/}



