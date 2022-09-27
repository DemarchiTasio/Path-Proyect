package com.Manager.pathExamination.Service;

import java.util.List;

import com.Manager.pathExamination.model.Alumno;

public interface AlumnoService {
    public void saveAlumno(Alumno a);
    public List<Alumno> findAllAlumno();
    public Alumno findByIdAlumno(int id);
    public void deleteAlumno(int id);
}
