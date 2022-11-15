package com.archerprop.appinventorypc.entidad;

import java.io.Serializable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

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

    private String lastArticulo;
    private Date vigencia;
    private int Empleado;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "inventario_articulo", joinColumns = @JoinColumn(name = "idInventario"), inverseJoinColumns = @JoinColumn(name = "serial"))
    private List<Articulos> articulos = new ArrayList<Articulos>();
}
