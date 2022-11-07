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
import com.Manager.pathExamination.Repository.EstadoRepository;
import com.Manager.pathExamination.Repository.PaisRepository;
import com.Manager.pathExamination.Service.InstitucionService;
import com.Manager.pathExamination.ServiceImplement.UsuarioServiceImpl;

@Controller

public class InstitucionController {

    @Autowired
    private InstitucionService institucionService;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    // ---------------------------------Informes-------------------------------------------

    @GetMapping("/reports")
    public String informeList(Model model) {
        List<Institucion> instituciones = institucionService.findAllInstitucion();
        model.addAttribute("listInstitucion", instituciones);
        model.addAttribute("listEstado", estadoRepository.findAll());
        return "informeTemplate/infoIndex";
    }

    @GetMapping("/reports/addReport")
    public String agregar(Institucion i, Model model) {
        model.addAttribute("listPais", paisRepository.findAll());
        return "informeTemplate/infoForm";
    }

    @PostMapping("/reports/saveReport")
    public String guardar(Institucion i) {

        if (i.getEstado() == null) {
            i.setEstado(estadoRepository.findById(1).orElse(null));
            institucionService.saveInstitucion(i);
        } else {
            institucionService.saveInstitucion(i);
        }
        return "redirect:/reports";
    }

    @GetMapping("/reports/editReport/{id_institucion}")
    public String editar(Institucion i, Model model) {
        i = institucionService.findByIdInstitucion(i.getId_institucion());
        model.addAttribute("institucion", i);
        return "informeTemplate/infoForm";
    }

    @GetMapping("/reports/deleteReport/{id_institucion}")
    public String eliminar(@PathVariable(name = "id_institucion") int id) {
        institucionService.deleteInstitucion(id);
        return "redirect:/reports";
    }

    // ---------------------------------Instituciones-------------------------------------------

    @GetMapping("/institutions")
    public String institucionesList(Model model) {
        List<Institucion> instituciones = institucionService.findAllInstitucion();
        model.addAttribute("listInstitucion", instituciones);
        return "institucionTemplate/instIndex";
    }

    @GetMapping("/institutions/addInstitution")
    public String agregarInstitucion(Institucion i, Model model) {
        return "institucionTemplate/instForm";
    }

    @PostMapping("/institutions/saveInstitution")
    public String guardarInstitucion(Institucion i) {
        institucionService.saveInstitucion(i);
        institucionService.setContrato(i);
        return "redirect:/institutions";
    }

    @GetMapping("/institutions/editInstitution/{id_institucion}")
    public String editarInstitucion(Institucion i, Model model) {
        i = institucionService.findByIdInstitucion(i.getId_institucion());
        model.addAttribute("institucion", i);
        return "institucionTemplate/instForm";
    }

    @GetMapping("/institutions/deleteInstitution/{id_institucion}")
    public String eliminarInstitucion(@PathVariable(name = "id_institucion") int id) {
        institucionService.deleteInstitucion(id);
        return "redirect:/institutions";
    }

    // ---------------------------------Seteo de Variables-------------------------------------------

    @GetMapping("/reports/nextState/{id_institucion}")
    public String siguienteEstado(@PathVariable(name = "id_institucion") int id) {
        institucionService.setEstadoSiguiente(institucionService.findByIdInstitucion(id));
        return "redirect:/reports";
    }

    @PostMapping("/reports/contactTime")
    public String setTiempoContacto(@RequestParam(name = "id_institucion") int id,
            @RequestParam(name = "tiempo_contacto") String dateTime) {
        institucionService.setTiempoContacto(id, dateTime);
        // institucionService.setEstadoSiguiente(institucionService.findByIdInstitucion(id));
        return "redirect:/reports";
    }

    @PostMapping("/reports/meet")
    public String setReunion(@RequestParam(name = "id_institucion") int id,
            @RequestParam(name = "reuniones") String dateTime) {
        institucionService.setReunion(id, dateTime);
        // institucionService.setEstadoSiguiente(institucionService.findByIdInstitucion(id));
        return "redirect:/reports";
    }

    @PostMapping("/reports/contacted")
    public String setContactado(@RequestParam(name = "id_institucion") int id) {
        institucionService.setContactado(id);
        return "redirect:/reports";
    }

    @PostMapping("/reports/inscription")
    public String setInscription(@RequestParam(name = "id_institucion") int id) {

        Institucion i = institucionService.findByIdInstitucion(id);
        usuarioServiceImpl.generarUsuario(i.getEmail());
        
        return "redirect:/reports";
    }

}
