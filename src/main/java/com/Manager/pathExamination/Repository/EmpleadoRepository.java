package com.Manager.pathExamination.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Manager.pathExamination.Model.Empleado;

@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, Integer> {
    
}
