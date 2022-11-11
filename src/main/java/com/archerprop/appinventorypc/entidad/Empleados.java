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
public class Empleados implements Serializable {

    @Id
    private int idEmpleado;

    private String cargo;

}
