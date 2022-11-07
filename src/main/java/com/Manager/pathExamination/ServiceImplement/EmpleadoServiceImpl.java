package com.Manager.pathExamination.ServiceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Manager.pathExamination.Model.Empleado;
import com.Manager.pathExamination.Model.Usuario;
import com.Manager.pathExamination.Repository.EmpleadoRepository;
import com.Manager.pathExamination.Service.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    UsuarioServiceImpl usuarioServiceImpl;

    @Override
    public List<Empleado> findAllEmpleados() {

        return (List<Empleado>) empleadoRepository.findAll();
    }

    @Override
    public Empleado findEmpleadoById(int id) {

        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public void saveEmpleado(Empleado e) {
        
        empleadoRepository.save(e);
    }

    @Override
    public void deleteEmpleado(int id) {
        
        Empleado e = empleadoRepository.findById(id).orElse(null);
        e.setVisibilidad(true);
        usuarioServiceImpl.deshabilitarUsuario(e.getUsuario());
        empleadoRepository.save(e);
    }
    
}
