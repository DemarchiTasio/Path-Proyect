package com.Manager.pathExamination.Service;

import java.util.List;

import com.Manager.pathExamination.model.Alumno;
import com.Manager.pathExamination.model.Examen;
import com.Manager.pathExamination.model.Institucion;


public interface ExamenService {
    
    public void saveExamen(Examen e);
    public void asignarAlumno(Alumno a);
    public List<Examen> examenList();
    public List<Institucion> filtrarInstitucion(Examen e);
    public void setInstitucion(int id, int i);
    public void setAsistencia(int id, boolean asistencia);

}
