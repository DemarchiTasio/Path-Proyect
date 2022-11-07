package com.Manager.pathExamination.Service;

import java.util.Map;

public interface GraphService {
    

    public Map<String, Integer> informesInstituciones();
    public Map<String, Integer> alumnosPais();
    public Map<String, Integer> alumnAprobados();
    public Map<String, Integer> alumnosAsistencia();
}
