package com.Manager.pathExamination.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Manager.pathExamination.model.Pais;

@Repository
public interface PaisRepository extends CrudRepository<Pais, Integer> {
    
}
