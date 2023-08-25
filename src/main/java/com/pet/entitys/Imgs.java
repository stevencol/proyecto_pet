package com.pet.entitys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name= "imgs")
@Data
public class Imgs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @Column(name = "url")
    private String urls;

    @ManyToOne
    @JsonProperty("pet")
    @JoinColumn(name = "id_pet")
    private Pet pet;

    @JsonProperty("idcloud")
    @Column(name = "idCloud")
    private String idCloud;

  /*  @OneToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Person person;
*/
}
