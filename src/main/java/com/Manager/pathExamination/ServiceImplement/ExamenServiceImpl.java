package com.Manager.pathExamination.ServiceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.Manager.pathExamination.Model.Alumno;
import com.Manager.pathExamination.Model.Estado;
import com.Manager.pathExamination.Model.Examen;
import com.Manager.pathExamination.Model.Institucion;
import com.Manager.pathExamination.Repository.EstadoRepository;
import com.Manager.pathExamination.Repository.ExamenRepository;
import com.Manager.pathExamination.Repository.InstitucionRepository;
import com.Manager.pathExamination.Service.AlumnoService;
import com.Manager.pathExamination.Service.ExamenService;
import com.Manager.pathExamination.Service.InstitucionService;

@Service
public class ExamenServiceImpl implements ExamenService {

    @Autowired
    ExamenRepository examenRepository;

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    InstitucionRepository institucionRepository;

    @Override
    public void saveExamen(Examen e) {

        examenRepository.save(e);
    }

    @Override
    public List<Examen> listExamen() {

        return (List<Examen>) examenRepository.findAll();
    }

    @Override
    public void asigneInstitution(int id, Examen e) {

        Institucion i = institucionRepository.findById(id).orElse(null);
        e.setInstitucion(i);
    }

    @Override
    public Examen findExamenById(int id) {

        return examenRepository.findById(id).orElse(null);
    }

    @Override
    public void setInstitucion(Institucion i, Examen e) {

        e.setInstitucion(i);
        saveExamen(e);
    }

    @Override
    public void setAsistencia(int id) {

        Examen e = examenRepository.findById(id).orElse(null);
        e.setAsistencia(true);
        saveExamen(e);
    }

    @Override
    public void setEstado() {
        
        List<Examen> examenCertificado = (List<Examen>) examenRepository.findAll();
        
        for (Examen examen : examenCertificado) {

            if(examen.isAsistencia() && !examen.getAlumno().isVisibilidad()){

                examen.setEstado(estadoRepository.findById(6).orElse(null));
                examenRepository.save(examen);
            }else{
                
                examen.getAlumno().setVisibilidad(true);
            }
            
        }

    }
    
    @Override
    public void setEstadoSiguiente(Examen e) {

        Estado eActual = e.getEstado();
        Estado eAux = estadoRepository.findById(eActual.getId_estado() + 1).orElse(null);
        e.setEstado(eAux);
        examenRepository.save(e);

    }

    @Override
    public void setNota(Examen e, float nota) {
        
        e.setNota(nota);
        examenRepository.save(e);
    }

    @Override
    public void setEntrega(Examen e) {

        e.setEntregado(true);
        e.getAlumno().setVisibilidad(true);
        examenRepository.save(e);      
    }
}
