package com.Manager.pathExamination.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Manager.pathExamination.Model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    
}
