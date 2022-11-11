package com.archerprop.appinventorypc;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;
import com.archerprop.appinventorypc.entidad.Articulos;
import com.archerprop.appinventorypc.services.InventarioService;
import com.github.javafaker.Faker;

/**
 *
 * @author Aschente
 */
@SpringBootTest
@ActiveProfiles("test")
public class AppInventoryPCTest {

    @Autowired
    private InventarioService inventarioService;

    @Test
    public void testearServicio() {
        Faker faker = new Faker();
        Articulos articulo = new Articulos();
        articulo.setNombre(faker.name().fullName());
        articulo.setSerial(faker.code().isbn10());
        articulo.setStock(faker.number().randomDigit());
        articulo.setPrecioU(0);
        articulo.setPrecioB(0);
        articulo.setProveedor(000);
        assertTrue(inventarioService.crearArticulo(articulo));
    }

}
