package com.archerprop.appinventorypc.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archerprop.appinventorypc.entidad.Inventarios;

/**
 *
 * @author Aschente
 */
@Repository
public interface InventarioRepositorio
        extends JpaRepository<Inventarios, String> {

    public boolean findByArticulos(String serial);

}
