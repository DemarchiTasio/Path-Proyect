package com.Manager.pathExamination.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


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
    private String reuniones;

    @NotEmpty
    private String tipo_contrato;
    private String tipo;
    private String ciudad;
    private String provincia;
    private String pais;
    private String localidad;
    private String direccion;
    private String codigo_postal;
    private String email;
    private String telefono;
    private String facebook;
    private String web;
    private String instagram;
    private String nombre_director;
    private String apellido_director;
    private String nacimiento_director;
    private String documento_director;

    private int personal_disponible;
    private int cant_aulas;
    private int alumnos_aula;
    private int cant_baños;

    private boolean visibilidad;
    private boolean contrato;
    private boolean contactado;
}
