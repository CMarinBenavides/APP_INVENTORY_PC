package com.archerprop.appinventorypc.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archerprop.appinventorypc.entidad.Reportes;

/**
 *
 * @author Aschente
 */
@Repository
public interface ReporteRepositorio extends JpaRepository<Reportes, Integer> {

    public Reportes findBynumReporte(int numReporte);

    public boolean existsBynumReporte(int numReporte);
}
