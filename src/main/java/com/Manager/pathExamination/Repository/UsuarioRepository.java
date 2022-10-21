package com.Manager.pathExamination.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Manager.pathExamination.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    
}
