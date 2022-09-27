package com.Manager.pathExamination.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Manager.pathExamination.model.Estado;

@Repository
public interface EstadoRepository extends CrudRepository<Estado, Integer> {
    
}
