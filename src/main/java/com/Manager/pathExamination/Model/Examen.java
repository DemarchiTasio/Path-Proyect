package com.Manager.pathExamination.Model;

import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
    private String nivel_examinar;
    private String modulo;
    private float nota;
    private boolean asistencia;
    private boolean entregado;

    @ManyToOne
    private Institucion institucion;
    
    @ManyToOne
    private Alumno alumno;
    
    @OneToOne
    private Estado estado;

    public Examen(String nivel_examinar, String modulo, float nota, Alumno alumno, Estado estado) {
        this.nivel_examinar = nivel_examinar;
        this.modulo = modulo;
        this.nota = nota;
        this.alumno = alumno;
        this.estado = estado;
    }

    public Examen(String nivel_examinar, String modulo, float nota, Alumno alumno) {
        this.nivel_examinar = nivel_examinar;
        this.modulo = modulo;
        this.nota = nota;
        this.alumno = alumno;
    }

    public Examen() {
    }

    

}
