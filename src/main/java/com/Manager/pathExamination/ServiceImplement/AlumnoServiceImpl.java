package com.Manager.pathExamination.ServiceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Manager.pathExamination.Model.Alumno;
import com.Manager.pathExamination.Model.Examen;
import com.Manager.pathExamination.Model.Institucion;
import com.Manager.pathExamination.Repository.AlumnoRepository;
import com.Manager.pathExamination.Service.AlumnoService;
import com.Manager.pathExamination.Service.ExamenService;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private ExamenService examenService;

    @Override
    // @Transactional
    public void saveAlumno(Alumno a) {
        alumnoRepository.save(a);
        }


    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findAllAlumno() {
        
        return (List<Alumno>) alumnoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Alumno findByIdAlumno(int id) {
        
        return alumnoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteAlumno(int id) {
        Alumno a = findByIdAlumno(id);
        a.setVisibilidad(true);
        
    }


    @Override
    public void setInstitucion(Institucion i, Alumno a) {
        
        a.setInstitucion(i);
        
    }


    @Override
    public void setDatos(Alumno a) {
       
        a.setSet_info(true);
        alumnoRepository.save(a);
    }
    
}
