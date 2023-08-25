package com.pet.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet.entitys.Imgs;
import com.pet.enums.Sexo;
import com.pet.valid.ReceValidBean;
import com.pet.valid.SexBean;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Validated
public class PetPostDtoFIles {

    @JsonProperty("id")
    private Long id;
    @Size (min = 3 , max = 26, message = "*Mínimo 3 caracteres Máximo 20")
    @NotEmpty(message = "*No puede estar vacio")
    @Pattern(regexp = "[a-zA-ZñÑ ]*" , message = "Solo usa caracteres alfabéticos ")
    @JsonProperty("name")
    private String name;

    @Size(min = 5 , max = 10, message = "*Mínimo 6 caracteres Máximo 20")
    @NotEmpty(message = "*No puede estar vacio")
    @JsonProperty("age")
    private String age;


    @ManyToOne
    @JsonProperty("race")
    @ReceValidBean
    private RaceUseID race;


    @JsonProperty("imgs")
    private List<MultipartFile> imgs;



    @Enumerated(EnumType.STRING)
    @JsonProperty("type")
    private PetTypeUseId type;




    @SexBean
    @JsonProperty("sex")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull(message = "Sexo no valido :P")
    private Sexo sex;


    @NotNull(message = "Debes agregar una descripcion")
    @Pattern(regexp = "[a-zA-ZñÑáéíóúÁÉÍÓÚ,.\s]*" , message = "Solo usa caracteres alfabéticos ")
    @Size(message = "La descripcion debe contener minimo 16 caracteres", min = 16)
    @JsonProperty("description")
    private String description;

}
