package com.archerprop.appinventorypc.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archerprop.appinventorypc.entidad.Usuarios;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuarios, Integer> {

    public boolean findByCedula(int cedula);

    public boolean findByCorreo(String correo);;

    public List<Usuarios> findByNombreContaining(String nombre);

    public List<Usuarios> findByApellidoContaining(String apellido);

}
