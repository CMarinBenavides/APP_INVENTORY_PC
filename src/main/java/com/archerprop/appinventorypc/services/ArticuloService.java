package com.archerprop.appinventorypc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder.In;

import lombok.extern.log4j.Log4j2;
import com.archerprop.appinventorypc.entidad.Articulos;
import com.archerprop.appinventorypc.repositorios.ArticuloRepositorio;

@Service
@Log4j2
public class ArticuloService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ArticuloRepositorio articuloRepositorio;

    @Transactional
    public boolean crearArticulo(Articulos articulo) {
        if (articulo == null) {
            return false;
        }
        if (articulo.getNombre() == null || articulo.getSerial() == null || articulo.getStock() <= 0
                || articulo.getPrecioU() <= 0 || articulo.getFechModi() == null || articulo.getProveedor() <= 0) {
            return false;
        }
        try {
            if (!articuloRepositorio.existsBySerial(articulo.getSerial())) {
                articuloRepositorio.save(articulo);
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            log.error("Error al crear el articulo: " + e.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean modificarArticulo(Articulos articulo) {
        if (articulo == null) {
            return false;
        }
        if (articulo.getNombre() == null || articulo.getSerial() == null || articulo.getStock() <= 0
                || articulo.getPrecioU() <= 0 || articulo.getFechModi() == null || articulo.getProveedor() <= 0) {
            return false;
        }
        try {
            if (articuloRepositorio.existsBySerial(articulo.getSerial())) {
                articuloRepositorio.save(articulo);
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            log.error("Error al modificar el articulo: " + e.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean eliminarArticulo(Articulos articulo) {
        if (articulo == null) {
            return false;
        }
        try {
            if (articuloRepositorio.existsBySerial(articulo.getSerial())) {
                articuloRepositorio.delete(articulo);
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            log.error("Error al eliminar el articulo: " + e.getMessage());
            return false;
        }
    }
}
