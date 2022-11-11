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
public class Reportes implements Serializable {

    @Id
    private int numReporte;

    private String nombre;
    private Date fechaR;
    private int inventario;
    private int empleado;
    private String articulo;
}
