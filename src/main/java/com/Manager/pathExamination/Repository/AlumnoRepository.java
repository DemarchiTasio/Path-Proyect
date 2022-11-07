package com.Manager.pathExamination.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Manager.pathExamination.Model.Alumno;

@Repository
public interface AlumnoRepository extends CrudRepository<Alumno, Integer> {
    
}
