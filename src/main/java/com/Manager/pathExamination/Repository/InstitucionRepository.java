package com.Manager.pathExamination.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Manager.pathExamination.Model.Institucion;

@Repository
public interface InstitucionRepository extends CrudRepository<Institucion, Integer >{
}