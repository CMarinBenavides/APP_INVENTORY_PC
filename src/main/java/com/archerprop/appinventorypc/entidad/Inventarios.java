package com.archerprop.appinventorypc.entidad;

import java.io.Serializable;

import java.sql.Date;
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
public class Inventarios implements Serializable {

    @Id
    private int idInventario;

    private String articulos;
    private String lastArticulo;
    private Date vigencia;
    private int Empleado;
}
