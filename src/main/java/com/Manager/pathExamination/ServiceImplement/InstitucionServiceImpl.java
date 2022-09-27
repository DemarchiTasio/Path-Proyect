package com.Manager.pathExamination.ServiceImplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Manager.pathExamination.Repository.EstadoRepository;
import com.Manager.pathExamination.Repository.InstitucionRepository;
import com.Manager.pathExamination.Service.InstitucionService;
import com.Manager.pathExamination.model.Estado;
import com.Manager.pathExamination.model.Institucion;

@Service
public class InstitucionServiceImpl implements InstitucionService {

    @Autowired
    InstitucionRepository institucionRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    @Transactional
    public void saveInstitucion(Institucion i) {
        institucionRepository.save(i);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Institucion> findAllInstitucion() {

        return (List<Institucion>) institucionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Institucion findByIdInstitucion(int id) {
        return institucionRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteInstitucion(int id) {
        Institucion i = findByIdInstitucion(id);
        i.setVisibilidad(true);
    }

    @Override
    @Transactional
    public void setEstadoSiguiente(Institucion i) {
        Estado eActual = i.getEstado();
        Estado eAux = estadoRepository.findById(eActual.getId_estado() + 1).orElse(null);
        i.setEstado(eAux);

    }
}