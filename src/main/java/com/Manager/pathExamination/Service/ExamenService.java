package com.Manager.pathExamination.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.Manager.pathExamination.Model.Examen;
import com.Manager.pathExamination.Model.Institucion;
import com.Manager.pathExamination.Repository.ExamenRepository;


public interface ExamenService {

    public void saveExamen(Examen e);
    public List<Examen> listExamen();
    public Examen findExamenById(int id);
    public void asigneInstitution(int id, Examen e);
    public void setInstitucion(Institucion i, Examen e);
    public void setAsistencia(int id);
    public void setEstado();
    public void setEstadoSiguiente(Examen i);
    public void setNota(Examen e, float nota);
    public void setEntrega(Examen e);
}
