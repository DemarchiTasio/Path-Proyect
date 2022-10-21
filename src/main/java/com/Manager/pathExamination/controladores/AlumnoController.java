package com.Manager.pathExamination.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Manager.pathExamination.Service.AlumnoService;
import com.Manager.pathExamination.Service.ExamenService;
import com.Manager.pathExamination.Service.InstitucionService;
import com.Manager.pathExamination.model.Alumno;

@Controller
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private ExamenService examenService;

    @Autowired
    private InstitucionService institucionService;

    @GetMapping("/alumnos")
    public String alumnoList(Model model){
        model.addAttribute("listAlumno", alumnoService.findAllAlumno());
        return "alumIndex";
    }

    @GetMapping("/agregar")
    public String agregarAlumno(Alumno a, Model model) {
        model.addAttribute("institucionList", institucionService.findAllInstitucion());
        return "alumForm";
    }

    @GetMapping("/alumnos/agregar")
    public String agregarSiguientesAlumno(Alumno a, Model model) {
        return "redirect:/agregar?siguiente";
    }

    @PostMapping("/alumnos/guardar")
    public String guardar(Alumno a) {
            alumnoService.saveAlumno(a);
            examenService.asignarAlumno(a);
        return "redirect:/alumnos";
    }

    @PostMapping("/alumnos/guardarFinalizado")
    public String guardarFinalizado(Alumno a) {
            alumnoService.saveAlumno(a);
        return "redirect:/alumnos";
    }

    @GetMapping("/alumnos/eliminar/{id_alumno}")
    public String alumnoDelete(@PathVariable(name = "id_alumno") int id){
        alumnoService.deleteAlumno(id);
        return "redirect:/alumnos";
    }

    @GetMapping("/alumnos/editar/{id_alumno}")
    public String editarAlumno(Alumno a, Model model) {
        a = alumnoService.findByIdAlumno(a.getId_alumno());
        model.addAttribute("alumno", a);
        return "alumForm";
    }
    
}
