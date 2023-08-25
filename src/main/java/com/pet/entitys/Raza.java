package com.pet.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "raza")
@Getter
@Setter
public class Raza {

    public Raza(){

    }

    public Raza(Long id, String name, List<Pet> pet) {
        this.id = id;
        this.name = name;
        this.pet = pet;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;



    @Column(name = "raza")
    private String name;

    @OneToMany(mappedBy = "race" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Pet> pet;

    @ManyToOne
    @JoinColumn(name = "id_type")
    PetType type;




}
