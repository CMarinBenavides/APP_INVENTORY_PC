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
public class Proveedores implements Serializable {

    @Id
    private int idProveedor;

    private String tipoProveedor;

}
