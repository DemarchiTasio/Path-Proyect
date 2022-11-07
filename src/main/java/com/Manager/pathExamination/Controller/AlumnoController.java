package com.Manager.pathExamination.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Manager.pathExamination.Model.Alumno;
import com.Manager.pathExamination.Model.Examen;
import com.Manager.pathExamination.Model.Institucion;
import com.Manager.pathExamination.Service.AlumnoService;
import com.Manager.pathExamination.Service.ExamenService;
import com.Manager.pathExamination.Service.InstitucionService;

@Controller
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private ExamenService examenService;

    @Autowired
    private InstitucionService institucionService;

    @GetMapping("/students")
    public String alumnoList(Model model) {
        model.addAttribute("listAlumno", alumnoService.findAllAlumno());
        return "alumnoTemplate/alumIndex";
    }

    @GetMapping("/students/load")
    public String agregarAlumno(Alumno a, Model model) {
        model.addAttribute("institucionList", institucionService.findAllInstitucion());
        return "alumnoTemplate/alumForm";
    }

    @PostMapping("/students/save")
    public String guardar(Alumno a, Institucion i, @RequestParam (name = "pais") String pais, @RequestParam (name = "provincia") String provincia) {

        

        if (a.getInstitucion() == null) {
            alumnoService.setInstitucion(i, a);
            alumnoService.saveAlumno(a);
        }if (a.getPais() != pais){
            a.setPais(pais);
            a.setProvincia(provincia);
            alumnoService.saveAlumno(a);
        }else {
            alumnoService.saveAlumno(a);
        }
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id_alumno}")
    public String alumnoDelete(@PathVariable(name = "id_alumno") int id) {
        alumnoService.deleteAlumno(id);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id_alumno}")
    public String editarAlumno(Alumno a, Examen e, Model model) {
        a = alumnoService.findByIdAlumno(a.getId_alumno());
        model.addAttribute("alumno", a);
        model.addAttribute("institucionList", institucionService.findAllInstitucion());
        return "alumnoTemplate/alumForm";
    }

}
