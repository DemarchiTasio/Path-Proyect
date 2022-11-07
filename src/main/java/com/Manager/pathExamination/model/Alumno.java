package com.Manager.pathExamination.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table (name = "alumno")
public class Alumno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_alumno;

    private String nombre;
    private String apellido;
    private String fecha_nacimiento;
    private int documento;
    private String email;
    private String telefono;
    private String provincia;
    private String pais;
    private String localidad;
    private boolean visibilidad;
    private boolean set_info;
    
    @ManyToOne
    private Institucion institucion;

   

}
