package com.archerprop.appinventorypc.entidad;

import java.io.Serializable;
import java.sql.Timestamp;

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

    private int usuario;
    private Timestamp fechaR;
}
