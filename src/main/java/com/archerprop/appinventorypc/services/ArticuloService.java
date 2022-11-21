package com.archerprop.appinventorypc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder.In;

import lombok.extern.log4j.Log4j2;
import com.archerprop.appinventorypc.entidad.Articulos;
import com.archerprop.appinventorypc.repositorios.ArticuloRepositorio;

/**
 *
 * @author Aschente
 */
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

    @Transactional
    public boolean verificar(Articulos articulo) {
        if (articulo == null) {
            return false;
        }
        try {
            if (articuloRepositorio.existsBySerial(articulo.getSerial())) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            log.error("Error al verificar el articulo: " + e.getMessage());
            return false;
        }
    }

    public List<Articulos> listarArticulos() {
        try {
            return articuloRepositorio.findAll();
        } catch (Exception e) {
            log.error("Error al listar los articulos: " + e.getMessage());
            return null;
        }
    }

    public List<Articulos> listarArticulosPorNombre(String nombre) {
        try {
            return articuloRepositorio.findByNombreContaining(nombre);
        } catch (Exception e) {
            log.error("Error al listar los articulos por nombre: " + e.getMessage());
            return null;
        }
    }

    public List<Articulos> listarArticulosPorProveedor(int proveedor) {
        try {
            List<Articulos> articulosPorProveedor = articuloRepositorio.findAll();
            if (articulosPorProveedor.isEmpty()) {
                return null;
            }
            // se crea nueva lista para almacenar los articulos que coincidan con el
            // proveedor
            List<Articulos> articulos = new ArrayList<Articulos>();
            for (Articulos articulo : articulosPorProveedor) {
                if (articulo.getProveedor() == proveedor) {
                    articulos.add(articulo);
                }
            }
            return articulos;
        } catch (Exception e) {
            log.error("Error al listar los articulos por proveedor: " + e.getMessage());
            return null;
        }
    }

    public Articulos obtenerArticuloPorSerial(String serial) {
        serial = serial.toUpperCase();
        try {
            return articuloRepositorio.findBySerial(serial);
        } catch (Exception e) {
            log.error("Error al obtener el articulo por serial: " + e.getMessage());
            return null;
        }
    }

}
