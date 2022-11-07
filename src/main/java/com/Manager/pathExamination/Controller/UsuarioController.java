package com.Manager.pathExamination.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Manager.pathExamination.Model.Empleado;
import com.Manager.pathExamination.Model.Usuario;
import com.Manager.pathExamination.Repository.RoleRepository;
import com.Manager.pathExamination.Service.EmpleadoService;
import com.Manager.pathExamination.ServiceImplement.UsuarioServiceImpl;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @Autowired
    private EmpleadoService empeladoService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/users")
    public String listarUsuarios(Model model){

        model.addAttribute("empleados", empeladoService.findAllEmpleados());

        return "usuarioTemplate/usuarioIndex";
    }

    @GetMapping("/users/singIn")
    public String registroUsuarioPage(Empleado e, Model modal){
        return "usuarioTemplate/usuarioForm";
    }

    @PostMapping("/users/singIn")
    public String registroUsuario(Empleado e, @RequestParam(value = "rol") String rol){
        usuarioServiceImpl.guardarUsuario(e,rol);
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id_empleado}")
    public String empleadoDelete(@PathVariable(name = "id_empleado") int id) {
        empeladoService.deleteEmpleado(id);
        return "redirect:/users";
    }

}
