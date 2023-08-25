package com.pet.entitys;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_roles")
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "person_id" , referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private  Person person;

    @JoinColumn(name = "rol_id" , referencedColumnName = "id_rol")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Role role;


}
