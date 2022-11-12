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
import com.archerprop.appinventorypc.repositorios.InventarioRepositorio;

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
    private InventarioRepositorio inventarioRepositorio;

    @Transactional
    public boolean agregarArticulo(Articulos articulo) {
        return false;
    }
}
