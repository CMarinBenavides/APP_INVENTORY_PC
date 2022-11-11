package com.archerprop.appinventorypc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import lombok.extern.log4j.Log4j2;
import com.archerprop.appinventorypc.entidad.Articulos;
import com.archerprop.appinventorypc.repositorios.InventarioArticuloRepositorio;

/**
 *
 * @author Aschente
 */
@Service
@Log4j2
public class InventarioService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private InventarioArticuloRepositorio inventarioArticuloRepositorio;

    @Transactional
    public boolean crearArticulo(Articulos articulo) {
        return false;
    }
}
