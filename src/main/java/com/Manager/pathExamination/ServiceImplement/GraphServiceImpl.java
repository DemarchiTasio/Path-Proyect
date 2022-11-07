package com.Manager.pathExamination.ServiceImplement;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Manager.pathExamination.Model.Alumno;
import com.Manager.pathExamination.Model.Examen;
import com.Manager.pathExamination.Model.Institucion;
import com.Manager.pathExamination.Repository.AlumnoRepository;
import com.Manager.pathExamination.Repository.ExamenRepository;
import com.Manager.pathExamination.Repository.InstitucionRepository;
import com.Manager.pathExamination.Service.GraphService;

@Service
public class GraphServiceImpl implements GraphService {

    @Autowired
    private InstitucionRepository institucionRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private ExamenRepository examenRepository;

    @Override
    public Map<String, Integer> informesInstituciones() {

        int cantReports = 0;
        int cantInst = 0;
        Map<String, Integer> reportInst = new LinkedHashMap<>();

        for (Institucion i : institucionRepository.findAll()) {

            cantReports++;
            if (i.isContrato()) {
                cantInst++;
            }

        }

        reportInst.put("Reports", cantReports);
        reportInst.put("Institutions", cantInst);

        return reportInst;
    }

    @Override
    public Map<String, Integer> alumnosPais() {

        int cantArgentina = 0;
        int cantBrasil = 0;
        int cantUruguay = 0;
        int cantChile = 0;
        Map<String, Integer> alumnosPais = new LinkedHashMap<>();

        for (Alumno a : alumnoRepository.findAll()) {

            if(a.getPais().equals("Argentina")){
                cantArgentina++;
            }else if(a.getPais().equals("Brasil")){
                cantBrasil++;
            }else if(a.getPais().equals("Uruguay")){
                cantUruguay++;
            }else if(a.getPais().equals("Chile")){
                cantChile++;
            }

        }

        alumnosPais.put("Argentina", cantArgentina);
        alumnosPais.put("Brasil", cantBrasil);
        alumnosPais.put("Uruguay", cantUruguay);
        alumnosPais.put("Chile", cantChile);

        return alumnosPais;
    }

    @Override
    public Map<String, Integer> alumnAprobados(){

        int inscripciones = 0;
        int aprobados = 0;
        Map<String, Integer> alumnosAprobados = new LinkedHashMap<>();

        for (Examen e : examenRepository.findAll()) {

            if(e.getNota()>=6){
                aprobados++;
            }

            inscripciones++;
        }

        alumnosAprobados.put("Inscriptions", inscripciones);
        alumnosAprobados.put("Passed", aprobados);

        return alumnosAprobados;

    } 

    @Override
    public Map<String, Integer> alumnosAsistencia(){

        int cantAlumno = 0;
        int cantAsistidos = 0;

        Map<String, Integer> alumnosasistencia = new LinkedHashMap<>();

        for (Examen e : examenRepository.findAll()) {

            if(e.isAsistencia()){
                cantAsistidos++;
            }

            cantAlumno++;
        }

        alumnosasistencia.put("attendance", cantAsistidos);
        alumnosasistencia.put("inscriptions", cantAlumno);

        return alumnosasistencia;
    }
}
