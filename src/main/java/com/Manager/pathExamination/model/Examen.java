package com.Manager.pathExamination.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name = "Examen")
public class Examen {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_examen;

    // @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    // @JoinTable(
    //     name = "examen_alumno",
    //     joinColumns = @JoinColumn(
    //         name = "examen_id", referencedColumnName = "id_examen"),
    //         inverseJoinColumns = @JoinColumn(name = "alumno_id", referencedColumnName = "id_alumno")
    //         )
    @ManyToOne
    private Alumno alumno;
    private String modulo;
    private float nota;
    @ManyToOne
    private Institucion institucion;
    private boolean asistencia;
}
