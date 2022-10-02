package com.Manager.pathExamination.model;

import java.util.Optional;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.time.*;
import java.util.Date;

import lombok.Data;


@Data
@Entity
@Table (name = "institucion")
public class Institucion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id_institucion;
    
    @ManyToOne
    private Estado estado;
    
    // @ManyToOne
    // private Pais pais;
    private String nombre;
    private String contacto;
    private String tiempo_contacto;

    @NotEmpty
    private String tipo_contrato;
    private String tipo;
    private String ciudad;
    private String provincia;
    private String pais;
    private String email;
    private String telefono;
    private boolean visibilidad;
    private boolean contrato;
}
