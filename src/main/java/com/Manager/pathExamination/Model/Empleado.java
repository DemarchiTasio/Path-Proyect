package com.Manager.pathExamination.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_empleado;
    private String nombre;
    private String apellido;
    private String email;
    private int documento;
    private String fecha_nacimineto;
    private boolean visibilidad;
    private String rol;

    @OneToOne
    private Usuario usuario;
}
