package com.Manager.pathExamination.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Manager.pathExamination.Service.AlumnoService;

@Controller
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/alumnos")
    public String alumnoList(Model model){
        model.addAttribute("listAlumno", alumnoService.findAllAlumno());
        return "alumIndex";
    }

    @GetMapping("/alumnos/eliminar/{id_alumno}")
    public String alumnoDelete(@PathVariable(name = "id_alumno") int id){
        alumnoService.deleteAlumno(id);
        return "redirect:/alumnos";
    }
}
