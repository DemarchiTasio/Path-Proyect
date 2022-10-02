package com.Manager.pathExamination.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.Manager.pathExamination.model.Estado;
import com.Manager.pathExamination.model.Institucion;

public interface InstitucionService {
    public void saveInstitucion(Institucion i);
    public List<Institucion> findAllInstitucion();
    public Institucion findByIdInstitucion(int id);
    public void deleteInstitucion(int id);
    public void setEstadoSiguiente(Institucion i);
    public void setTiempoContacto(int id, String dateTime);
}
