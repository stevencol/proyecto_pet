package com.pet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet.enums.Sexo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pet")
@Getter
@Setter
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    @Column(name = "name")
    private String name;


    @Column(name = "age")
    private String age;


    @ManyToOne
    @JoinColumn(name = "id_race")
    private Raza race;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pet", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("pet")
    private List<Imgs> img;

    @OneToMany(mappedBy = "pet")
    @JsonProperty("applications")
    private List<Applications> applications = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "id_type")
    private PetType type;

    @Column(name = "sex")
    private Sexo sexo;


    @Column(name = "description")
    private String description;


}
