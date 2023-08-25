package com.pet.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet.entitys.Imgs;
import com.pet.enums.Sexo;
import com.pet.valid.EmailBean;
import com.pet.valid.ReceValidBean;
import com.pet.valid.SexBean;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.validation.annotation.Validated;


import java.util.List;
@Data
@Validated
public class PetPostDto {

    @JsonProperty("id")
    private Long id;
    @Size (min = 3 , max = 26, message = "*Mínimo 3 caracteres Máximo 20")
    @NotEmpty(message = "*No puede estar vacio")
    @Pattern(regexp = "[a-zA-ZñÑ ]*" , message = "Solo usa caracteres alfabéticos ")
    @JsonProperty("name")
    private String name;

    @Size(min = 5 , max = 90, message = "*Mínimo 6 caracteres Máximo 50")
    @NotEmpty(message = "*No puede estar vacio")
    @JsonProperty("age")
    private String age;


    @ManyToOne
    @JsonProperty("race")
    @ReceValidBean
    private RaceUseID race;


    @JsonProperty("img")
    private List<Imgs> img;



    @Enumerated(EnumType.STRING)
    @JsonProperty("type")
    private PetTypeUseId type;




    @JsonProperty("sex")
    @NotNull(message = "Sexo no valido :P")
    private String sex;


    @NotNull(message = "Debes agregar una descripcion")
    @Pattern(regexp = "[a-zA-ZñÑáéíóúÁÉÍÓÚ,.\s]*" , message = "Solo usa caracteres alfabéticos ")
    @Size(message = "La descripcion debe contener minimo 16 caracteres", min = 16)
    @JsonProperty("description")
    private String description;

}
