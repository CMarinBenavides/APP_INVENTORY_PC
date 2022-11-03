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
public class Articulos implements Serializable {

    @Id
    private String serial;

    private String nombre;
    private int stock;
    private float precioU;
    private float precioB;
    private Date fecha;
}
