package com.Manager.pathExamination.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table (name = "rol")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_rol;

    @NotEmpty
    private String nombre;

    public Role(@NotEmpty String nombre) {
        this.nombre = nombre;
    }

    public Role() {
    }

    
}
