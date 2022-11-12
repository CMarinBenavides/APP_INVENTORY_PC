package com.archerprop.appinventorypc.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archerprop.appinventorypc.entidad.Articulos;

/**
 *
 * @author Aschente
 */
@Repository
public interface ArticuloRepositorio extends JpaRepository<Articulos, String> {

    public boolean findBySerial(String serial);

    public boolean findByNombre(int nombre);

    public boolean existsBySerial(String serial);

}
