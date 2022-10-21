package com.Manager.pathExamination.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Manager.pathExamination.Repository.EstadoRepository;
import com.Manager.pathExamination.Repository.PaisRepository;
import com.Manager.pathExamination.Service.InstitucionService;
import com.Manager.pathExamination.model.Institucion;

@Controller
public class InstitucionController {

    @Autowired
    private InstitucionService institucionService;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private PaisRepository paisRepository;

    // ---------------------------------Informes-------------------------------------------

    @GetMapping("/informes")
    public String informeList(Model model) {
        List<Institucion> instituciones = institucionService.findAllInstitucion();
        model.addAttribute("listInstitucion", instituciones);
        model.addAttribute("listEstado", estadoRepository.findAll());
        return "infoIndex";
    }

    @GetMapping("/informes/agregar")
    public String agregar(Institucion i, Model model) {
        model.addAttribute("listPais", paisRepository.findAll());
        return "infoForm";
    }

    @PostMapping("/informes/guardar")
    public String guardar(Institucion i) {

        if (i.getEstado() == null) {
            i.setEstado(estadoRepository.findById(1).orElse(null));
            institucionService.saveInstitucion(i);
        } else {
            institucionService.saveInstitucion(i);
        }
        return "redirect:/informes";
    }

    @GetMapping("/informes/editar/{id_institucion}")
    public String editar(Institucion i, Model model) {
        i = institucionService.findByIdInstitucion(i.getId_institucion());
        model.addAttribute("institucion", i);
        return "infoForm";
    }

    @GetMapping("/informes/eliminar/{id_institucion}")
    public String eliminar(@PathVariable(name = "id_institucion") int id) {
        institucionService.deleteInstitucion(id);
        return "redirect:/informes";
    }

    // ---------------------------------Instituciones-------------------------------------------

    @GetMapping("/instituciones")
    public String institucionesList(Model model) {
        List<Institucion> instituciones = institucionService.findAllInstitucion();
        model.addAttribute("listInstitucion", instituciones);
        return "instIndex";
    }

    @GetMapping("/instituciones/agregar")
    public String agregarInstitucion(Institucion i, Model model) {
        return "instForm";
    }

    @PostMapping("/instituciones/guardar")
    public String guardarInstitucion(Institucion i) {
        institucionService.setContrato(i);
        institucionService.saveInstitucion(i);

        return "redirect:/instituciones";
    }

    @GetMapping("/institucion/editar/{id_institucion}")
    public String editarInstitucion(Institucion i, Model model) {
        i = institucionService.findByIdInstitucion(i.getId_institucion());
        model.addAttribute("institucion", i);
        return "instForm";
    }

    @GetMapping("/institucion/eliminar/{id_institucion}")
    public String eliminarInstitucion(@PathVariable(name = "id_institucion") int id) {
        institucionService.deleteInstitucion(id);
        return "redirect:/instituciones";
    }

    // ---------------------------------Seteo de Variables-------------------------------------------

    @GetMapping("/informes/siguienteEstado/{id_institucion}")
    public String siguienteEstado(@PathVariable(name = "id_institucion") int id) {
        institucionService.setEstadoSiguiente(institucionService.findByIdInstitucion(id));
        return "redirect:/informes";
    }

    @PostMapping("/informes/tiempoContacto")
    public String setTiempoContacto(@RequestParam(name = "id_institucion") int id,
            @RequestParam(name = "tiempo_contacto") String dateTime) {
        institucionService.setTiempoContacto(id, dateTime);
        // institucionService.setEstadoSiguiente(institucionService.findByIdInstitucion(id));
        return "redirect:/informes";
    }

    @PostMapping("/informes/reunion")
    public String setReunion(@RequestParam(name = "id_institucion") int id,
            @RequestParam(name = "reuniones") String dateTime) {
        institucionService.setReunion(id, dateTime);
        // institucionService.setEstadoSiguiente(institucionService.findByIdInstitucion(id));
        return "redirect:/informes";
    }

    @PostMapping("/informes/contactado")
    public String setContactado(@RequestParam(name = "id_institucion") int id) {
        institucionService.setContactado(id);
        return "redirect:/informes";
    }

    

}
