package com.Manager.pathExamination.model;

import java.util.Collection;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Data
@Table(name = "usuario", uniqueConstraints  = @UniqueConstraint(columnNames = "usuario"))
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;
    private String usuario;
    private String contraseña;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
        name = "usuarios_perfil",
        joinColumns = @JoinColumn(
            name = "usuario_id", referencedColumnName = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id", referencedColumnName = "id_perfil")
            )
    private Collection<Perfil> perfiles;
}
