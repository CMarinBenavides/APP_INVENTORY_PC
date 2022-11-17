package com.archerprop.appinventorypc.entidad;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
    private Timestamp fechm;
    private int proveedor;

}
