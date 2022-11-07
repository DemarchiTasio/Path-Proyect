package com.Manager.pathExamination.ServiceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Manager.pathExamination.Model.Estado;
import com.Manager.pathExamination.Model.Institucion;
import com.Manager.pathExamination.Repository.EstadoRepository;
import com.Manager.pathExamination.Repository.InstitucionRepository;
import com.Manager.pathExamination.Service.InstitucionService;

@Service
public class InstitucionServiceImpl implements InstitucionService {

    @Autowired
    InstitucionRepository institucionRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    // ---------------------------------CRUD-------------------------------------------
    @Override
    // @Transactional
    public void saveInstitucion(Institucion i) {
        institucionRepository.save(i);
    }

    @Override
    // @Transactional(readOnly = true)
    public List<Institucion> findAllInstitucion() {

        return (List<Institucion>) institucionRepository.findAll();
    }

    @Override
    // @Transactional(readOnly = true)
    public Institucion findByIdInstitucion(int id) {
        return institucionRepository.findById(id).orElse(null);
    }

    @Override
    // @Transactional
    public void deleteInstitucion(int id) {
        Institucion i = findByIdInstitucion(id);
        i.setVisibilidad(true);
        saveInstitucion(i);
    }

    // ---------------------------------Seteo de
    // Variables-------------------------------------------

    @Override
    // @Transactional
    public void setEstadoSiguiente(Institucion i) {
        Estado eActual = i.getEstado();
        Estado eAux = estadoRepository.findById(eActual.getId_estado() + 1).orElse(null);
        i.setEstado(eAux);

    }

    @Override
    public void setTiempoContacto(int id, String dateTime) {
        Institucion i = institucionRepository.findById(id).orElse(null);
        i.setTiempo_contacto(dateTime);
        setEstadoSiguiente(i);
        saveInstitucion(i);
    }

    @Override
    public void setReunion(int id, String dateTime) {
        Institucion i = institucionRepository.findById(id).orElse(null);
        i.setReuniones(dateTime);
        setEstadoSiguiente(i);
        saveInstitucion(i);

    }

    @Override
    public void setContactado(int id) {
        Institucion i = institucionRepository.findById(id).orElse(null);
        i.setContactado(true);
        setEstadoSiguiente(i);
        saveInstitucion(i);
    }

    @Override
    public void setContrato(Institucion i) {
        i.setContrato(true);
        saveInstitucion(i);
    }
}