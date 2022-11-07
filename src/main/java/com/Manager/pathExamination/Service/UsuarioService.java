package com.Manager.pathExamination.Service;

import com.Manager.pathExamination.Model.Empleado;
import com.Manager.pathExamination.Model.Usuario;

public interface UsuarioService {
    
    public void guardarUsuario(Empleado e, String rol);

    public void guardarUsuarioInstitucion(Usuario u);
    public void generarUsuario(String email);
    
}
