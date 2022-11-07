package com.Manager.pathExamination.Service;

import java.util.List;

import com.Manager.pathExamination.Model.Empleado;

public interface EmpleadoService {
    
    public List<Empleado> findAllEmpleados();
    public Empleado findEmpleadoById(int id);
    public void saveEmpleado(Empleado e);
    public void deleteEmpleado(int id);
}
