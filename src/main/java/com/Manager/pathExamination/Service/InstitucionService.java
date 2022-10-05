package com.Manager.pathExamination.Service;

import java.util.List;

import com.Manager.pathExamination.model.Institucion;

public interface InstitucionService {

    //--------CRUD--------
    public void saveInstitucion(Institucion i);
    public List<Institucion> findAllInstitucion();
    public Institucion findByIdInstitucion(int id);
    public void deleteInstitucion(int id);

    //--------Seteo de Variables--------
    public void setEstadoSiguiente(Institucion i);
    public void setTiempoContacto(int id, String dateTime);
    public void setReunion(int id, String dateTime);
    public void setContactado(int id);
    public void setContrato(Institucion i);
}
