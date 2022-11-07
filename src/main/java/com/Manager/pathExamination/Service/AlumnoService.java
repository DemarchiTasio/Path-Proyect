package com.Manager.pathExamination.Service;

import java.util.List;

import com.Manager.pathExamination.Model.Alumno;
import com.Manager.pathExamination.Model.Institucion;

public interface AlumnoService {
    public void saveAlumno(Alumno a);
    public List<Alumno> findAllAlumno();
    public Alumno findByIdAlumno(int id);
    public void deleteAlumno(int id);
    public void setInstitucion(Institucion i, Alumno a);
    public void setDatos(Alumno a);
}
