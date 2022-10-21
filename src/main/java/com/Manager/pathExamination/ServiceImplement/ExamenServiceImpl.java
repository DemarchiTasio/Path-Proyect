package com.Manager.pathExamination.ServiceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Manager.pathExamination.Repository.ExamenRepository;
import com.Manager.pathExamination.Service.ExamenService;
import com.Manager.pathExamination.Service.InstitucionService;
import com.Manager.pathExamination.model.Alumno;
import com.Manager.pathExamination.model.Examen;
import com.Manager.pathExamination.model.Institucion;

@Service
public class ExamenServiceImpl implements ExamenService {

    @Autowired
    ExamenRepository examenRepository;

    @Autowired
    InstitucionService institucionService;

    @Override
    public void saveExamen(Examen e) {
        
        examenRepository.save(e);
    }

    @Override
    public void asignarAlumno(Alumno a) {

        Examen e = new Examen();
        e.setAlumno(a);
        examenRepository.save(e);
    }

    @Override
    public List<Examen> examenList() {
        
        return (List<Examen>) examenRepository.findAll();
    }

    @Override
    public List<Institucion> filtrarInstitucion(Examen e) {
        
        List<Institucion> listaFiltrada = null;
        List<Institucion> listaInstitucion = institucionService.findAllInstitucion();

        for (Institucion institucion : listaInstitucion) {
            if(institucion.getProvincia().equals(e.getAlumno().getProvincia())){
                listaFiltrada.add(institucion);
            }
        }

        return listaFiltrada;
    }

    @Override
    public void setInstitucion(int id, int i) {
        Examen e = examenRepository.findById(id).orElse(null);
        e.setInstitucion(institucionService.findByIdInstitucion(i));
        saveExamen(e);
        
    }

    @Override
    public void setAsistencia(int id, boolean asistencia) {
        Examen e = examenRepository.findById(id).orElse(null);
        e.setAsistencia(asistencia);
        saveExamen(e);
        
    }

}
