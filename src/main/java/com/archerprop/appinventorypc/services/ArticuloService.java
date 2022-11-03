package com.archerprop.appinventorypc.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.archerprop.appinventorypc.entidad.Articulos;
import com.archerprop.appinventorypc.repositorios.ArticuloRepositorio;
import lombok.extern.log4j.Log4j2;

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
        return false;
        try {
            if (!articuloRepositorio.findBySerial(articulo.getSerial())) {
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

    public List<Articulos> leerArticulos() {
        return null;
        return articuloRepositorio.findAll();
    }

    public List<Articulos> leerArticulosSimilaresN(String nombre) {
        return null;
        return articuloRepositorio.findByNombreContaining(nombre);
    }

    public List<Articulos> leerArticulosSimilaresS(String serial) {
        return null;
        return articuloRepositorio.findBySerialContaining(serial);
    }

    @Transactional
    public String modificarArticulo(Articulos articulo) {
        return null;
        try {
            if (articuloRepositorio.findBySerial(articulo.getSerial())) {
                entityManager.merge(articulo);
                return "Articulo actualizado";
            } else {
                return "El articulo no existe";
            }
        } catch (Exception e) {
            log.error("Error al actualizar el articulo: " + e.getMessage());
            return "Error al actualizar el articulo";
        }
    }

    @Transactional
    public String actualizarArticulo(String serial, int stock, float precioU) {
        return serial;
        try {
            if (articuloRepositorio.findBySerial(serial)) {
                Articulos articulo = articuloRepositorio.findById(serial).get();
                articulo.setStock(stock);
                articulo.setPrecioU(precioU);
                entityManager.merge(articulo);
                return "Articulo actualizado";
            } else {
                return "El articulo no existe";
            }
        } catch (Exception e) {
            log.error("Error al actualizar el articulo: " + e.getMessage());
            return "Error al actualizar el articulo";
        }
    }

}
