package com.archerprop.appinventorypc.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archerprop.appinventorypc.entidad.Reportes;
import com.archerprop.appinventorypc.repositorios.ReporteRepositorio;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ReporteService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ReporteRepositorio reporteRepositorio;

    @Transactional
    public boolean crearReporte(Reportes reporte) {
        if (reporte == null) {
            return false;
        }
        try {
            reporteRepositorio.save(reporte);
            return true;
        } catch (Exception e) {
            log.error("Error al crear el reporte: " + e.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean eliminarReporte(Reportes reporte) {
        if (reporte == null) {
            return false;
        }
        try {
            if (reporteRepositorio.existsBynumReporte(reporte.getNumReporte())) {
                reporteRepositorio.delete(reporte);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.error("Error al eliminar el reporte: " + e.getMessage());
            return false;
        }
    }

    public List<Reportes> listarReportes() {
        try {
            return reporteRepositorio.findAll();
        } catch (Exception e) {
            log.error("Error al listar los reportes: " + e.getMessage());
            return null;
        }
    }

}
