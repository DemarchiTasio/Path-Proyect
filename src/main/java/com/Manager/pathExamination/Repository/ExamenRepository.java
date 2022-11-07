package com.Manager.pathExamination.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Manager.pathExamination.Model.Examen;

@Repository
public interface ExamenRepository extends CrudRepository<Examen, Integer>{
    
}
