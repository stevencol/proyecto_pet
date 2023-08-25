package com.pet.entitys;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity@Getter@Setter
@Data
@Table(name= "rol")
public class Role {

    @Id
    @Column(name = "id_rol")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private  String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    List<UserRoles> roles;
}
