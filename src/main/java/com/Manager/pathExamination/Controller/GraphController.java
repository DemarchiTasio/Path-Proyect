package com.Manager.pathExamination.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Manager.pathExamination.Service.GraphService;

@Controller
public class GraphController {
    
    @Autowired
    private GraphService graphService;

    @GetMapping("/charts")
    public String chartGenerator(Model model){

        model.addAttribute("reportInst", graphService.informesInstituciones());

        model.addAttribute("alumnosPais", graphService.alumnosPais());

        model.addAttribute("alumnosAprobados", graphService.alumnAprobados().get("Passed"));

        model.addAttribute("alumnosInscriptos", graphService.alumnAprobados().get("Inscriptions"));

        model.addAttribute("inscripciones", graphService.alumnosAsistencia().get("inscriptions"));
        model.addAttribute("asistencias", graphService.alumnosAsistencia().get("attendance"));


        return "reportes";
    }

}
