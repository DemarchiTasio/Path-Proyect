package com.Manager.pathExamination.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Manager.pathExamination.Model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByUsername(String username);
}
