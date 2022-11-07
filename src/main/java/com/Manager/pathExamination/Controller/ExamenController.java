package com.Manager.pathExamination.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Manager.pathExamination.Model.Institucion;
import com.Manager.pathExamination.Repository.AlumnoRepository;
import com.Manager.pathExamination.Repository.EstadoRepository;
import com.Manager.pathExamination.Repository.InstitucionRepository;
import com.Manager.pathExamination.Service.AlumnoService;
import com.Manager.pathExamination.Service.ExamenService;
import com.Manager.pathExamination.Service.InstitucionService;
import com.Manager.pathExamination.Model.Examen;

@Controller
public class ExamenController {

    @Autowired
    ExamenService examenService;

    @Autowired
    AlumnoService alumnoService;

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    InstitucionService institucionService;

    @GetMapping("/exams")
    public String listExamn(Model model) {

        model.addAttribute("examenes", examenService.listExamen());
        model.addAttribute("instituciones", institucionService.findAllInstitucion());

        return "examenTemplate/examIndex";
    }

    @GetMapping("/dayOfExams")
    public String listDayOfExam(Model model) {

        model.addAttribute("examenes", examenService.listExamen());
        model.addAttribute("instituciones", institucionService.findAllInstitucion());

        return "examenTemplate/examDayIndex";
    }

    @GetMapping("/certificates")
    public String listCertificates(Model model) {

        model.addAttribute("listCertificados", examenService.listExamen());

        return "certificadosTemplate/certIndex";
    }

    // ---------------------------------Seteo de modulo y
    // nivel-------------------------------------------

    @PostMapping("/examns/service")
    public String saveExamen(@RequestParam(name = "id_alumno") int id,
            @RequestParam(name = "nivel_examinar") String nivel, @RequestParam(name = "modulo") String modulo) {

        Examen e = new Examen(nivel, modulo, 0, alumnoService.findByIdAlumno(id));
        examenService.saveExamen(e);
        alumnoService.setDatos(alumnoService.findByIdAlumno(id));
        return "redirect:/students";
    }

    // ---------------------------------Seteo de
    // Asistencia-------------------------------------------

    @PostMapping("/dayOfExams/attendance")
    public String setCertificate(@RequestParam(name = "id_examen") int id) {

        examenService.setAsistencia(id);
        return "redirect:/dayOfExams";
    }
    // ---------------------------------Seteo de
    // Institucion-------------------------------------------

    @PostMapping("/exams/institution")
    public String setInstitution(Institucion i, @RequestParam(name = "id_examen") int id) {

        examenService.setInstitucion(i, examenService.findExamenById(id));

        return "redirect:/exams";
    }

    // ---------------------------------Seteo de
    // Certificado "Fin dia de Examen"-------------------------------------------

    @PostMapping("/certificate/save")
    public String createCertificate() {

        examenService.setEstado();

        return "redirect:/dayOfExams";
    }

    // ---------------------------------Seteo Certificado siguiente
    // Estado-------------------------------------------
    @PostMapping("/certificate/nextState")
    public String setSiguienteEstado(@RequestParam(name = "id_examen") int id) {

        examenService.setEstadoSiguiente(examenService.findExamenById(id));

        return "redirect:/certificates";
    }

    @PostMapping("/certificate/setNota")
    public String setNota(@RequestParam(name = "id_examen") int id,@RequestParam(name = "nota") float nota) {

        examenService.setNota(examenService.findExamenById(id), nota);
        examenService.setEstadoSiguiente(examenService.findExamenById(id));

        return "redirect:/certificates";
    }

    @PostMapping("/certificate/setDelivered")
    public String setDelivered(@RequestParam(name = "id_examen") int id) {

        examenService.setEntrega(examenService.findExamenById(id));

        return "redirect:/certificates";
    }
}
