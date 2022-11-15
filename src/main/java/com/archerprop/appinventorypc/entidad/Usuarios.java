package com.archerprop.appinventorypc.entidad;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Aschente
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuarios implements Serializable {

    @Id
    private int cedula;

    private String nombre;
    private String apellido;
    private String correo;
    private String clave;
    private int tipoE;
    private int tipoP;

}
