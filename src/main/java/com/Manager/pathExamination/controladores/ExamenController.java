package com.Manager.pathExamination.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Manager.pathExamination.Service.AlumnoService;
import com.Manager.pathExamination.Service.ExamenService;
import com.Manager.pathExamination.Service.InstitucionService;
import com.Manager.pathExamination.model.Examen;
import com.Manager.pathExamination.model.Institucion;

@Controller
public class ExamenController {

    @Autowired
    ExamenService examenService;

    @Autowired
    InstitucionService institucionService;

     // ---------------------------------Seteo de Institucion-------------------------------------------

    @GetMapping("/examenes")
    public String listExamenes(Model model){

        List<Examen> examenesList = examenService.examenList();
        List<Institucion> instituciones = institucionService.findAllInstitucion();

        model.addAttribute("examenes", examenesList);
        model.addAttribute("instituciones", instituciones);
        
        return "examIndex";
    }

    @GetMapping

    @PostMapping("/examenes/save")
    public String setInstitucion(Examen e){

        examenService.saveExamen(e);

        return "redirect:/examenes";
    }
    

    @PostMapping("/examenes/institucion")
    public String setInstitucion(@RequestParam(name = "id_examen") int id,
            @RequestParam(name = "setInstitucion") int i) {
                examenService.setInstitucion(id, i);
        // institucionService.setEstadoSiguiente(institucionService.findByIdInstitucion(id));
        return "redirect:/examenes";
    }

     // ---------------------------------Dia de Examen-------------------------------------------

     @GetMapping("/examenesDay")
    public String listDiaExamenes(Model model){

        List<Examen> examenesList = examenService.examenList();
        List<Institucion> instituciones = institucionService.findAllInstitucion();

        model.addAttribute("examenes", examenesList);
        model.addAttribute("instituciones", instituciones);
        
        return "examDayIndex";
    }

    @PostMapping("/examenesDay/save")
    public String examenDaySave(Examen e){

        examenService.saveExamen(e);

        return "redirect:/examenesDay";
    }

    @PostMapping("/examenes/asistencia")
    public String setAsisencia(@RequestParam(name = "id_examen") int id) {
                examenService.setAsistencia(id, true);
        // institucionService.setEstadoSiguiente(institucionService.findByIdInstitucion(id));
        return "redirect:/examenesDay";
    }
}
