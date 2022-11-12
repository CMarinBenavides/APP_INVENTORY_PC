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
import com.archerprop.appinventorypc.services.ArticuloService;
import com.archerprop.appinventorypc.services.InventarioService;
import com.github.javafaker.Faker;
import java.sql.Date;

/**
 *
 * @author Aschente
 */
@SpringBootTest
@ActiveProfiles("test")
public class AppInventoryPCTest {

    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private InventarioService inventarioService;

    @Test
    public void testearServicioCrearArticulo() {
        Faker faker = new Faker();
        Articulos articulo = new Articulos();
        articulo.setNombre(faker.name().fullName());
        articulo.setSerial("h");
        articulo.setStock(10);
        articulo.setPrecioU(10);
        articulo.setFechModi(new Date(1));
        articulo.setProveedor(10);

        Boolean articuloCreado = articuloService.crearArticulo(articulo);
        assertTrue(articuloCreado);
    }

    @Test
    public void testearServicioCrearArticuloNull() {
        Articulos articulo = null;
        Boolean articuloCreado = articuloService.crearArticulo(articulo);
        assertFalse(articuloCreado);
    }

    @Test
    public void testearServicioCrearArticuloComponentesNull() {
        Articulos articulo = new Articulos();
        articulo.setNombre(null);
        articulo.setSerial(null);
        articulo.setStock(0);
        articulo.setPrecioU(0);
        articulo.setFechModi(null);
        articulo.setProveedor(0);

        Boolean articuloCreado = articuloService.crearArticulo(articulo);
        assertFalse(articuloCreado);
    }

    @Test
    public void testearServicioCrearArticuloStockPrecioUProveedorNegativo() {
        Faker faker = new Faker();
        Articulos articulo = new Articulos();
        articulo.setNombre(faker.name().fullName());
        articulo.setSerial(faker.code().isbn10());
        articulo.setStock(-10);
        articulo.setPrecioU(-10);
        articulo.setFechModi(new Date(1));
        articulo.setProveedor(-10);

        Boolean articuloCreado = articuloService.crearArticulo(articulo);
        assertFalse(articuloCreado);
    }

    @Test
    public void testearServicioCrearArticuloYaExiste() {
        Faker faker = new Faker();
        Articulos articulo = new Articulos();
        articulo.setNombre(faker.name().fullName());
        articulo.setSerial(faker.code().isbn10());
        articulo.setStock(10);
        articulo.setPrecioU(10);
        articulo.setFechModi(new Date(1));
        articulo.setProveedor(10);

        Boolean articuloCreado = articuloService.crearArticulo(articulo);
        assertTrue(articuloCreado);

        articuloCreado = articuloService.crearArticulo(articulo);
        assertFalse(articuloCreado);
    }

    @Test
    public void testearServicioModificarArticulo() {
        Faker faker = new Faker();
        Articulos articulo = new Articulos();
        articulo.setNombre(faker.name().fullName());
        articulo.setSerial("hola");
        articulo.setStock(10);
        articulo.setPrecioU(10);
        articulo.setFechModi(new Date(1));
        articulo.setProveedor(10);

        Boolean articuloCreado = articuloService.crearArticulo(articulo);
        assertTrue(articuloCreado);

        articulo.setNombre(faker.name().fullName());
        articulo.setSerial("hola");
        articulo.setStock(10);
        articulo.setPrecioU(10);
        articulo.setFechModi(new Date(1));
        articulo.setProveedor(10);

        Boolean articuloModificado = articuloService.modificarArticulo(articulo);
        assertTrue(articuloModificado);
    }

    @Test
    public void testearServicioModificarArticuloNull() {
        Articulos articulo = null;
        Boolean articuloModificado = articuloService.modificarArticulo(articulo);
        assertFalse(articuloModificado);
    }

    @Test
    public void testearServicioModificarArticuloComponentesNull() {
        Faker faker = new Faker();
        Articulos articulo = new Articulos();
        articulo.setNombre(faker.name().fullName());
        articulo.setSerial("hola");
        articulo.setStock(10);
        articulo.setPrecioU(10);
        articulo.setFechModi(new Date(1));
        articulo.setProveedor(10);

        Boolean articuloCreado = articuloService.crearArticulo(articulo);
        assertTrue(articuloCreado);

        articulo.setNombre(null);
        articulo.setSerial(null);
        articulo.setStock(0);
        articulo.setPrecioU(0);
        articulo.setFechModi(null);
        articulo.setProveedor(0);

        Boolean articuloModificado = articuloService.modificarArticulo(articulo);
        assertFalse(articuloModificado);
    }

    @Test
    public void testearServicioModificarArticuloStockPrecioUProveedorNegativo() {
        Faker faker = new Faker();
        Articulos articulo = new Articulos();
        articulo.setNombre(faker.name().fullName());
        articulo.setSerial("hola");
        articulo.setStock(10);
        articulo.setPrecioU(10);
        articulo.setFechModi(new Date(1));
        articulo.setProveedor(10);

        Boolean articuloCreado = articuloService.crearArticulo(articulo);
        assertTrue(articuloCreado);

        articulo.setNombre(faker.name().fullName());
        articulo.setSerial("hola");
        articulo.setStock(-10);
        articulo.setPrecioU(-10);
        articulo.setFechModi(new Date(1));
        articulo.setProveedor(-10);

        Boolean articuloModificado = articuloService.modificarArticulo(articulo);
        assertFalse(articuloModificado);
    }

    @Test
    public void testearServicioModificarArticuloNoExiste() {
        Faker faker = new Faker();
        Articulos articulo = new Articulos();
        articulo.setNombre(faker.name().fullName());
        articulo.setSerial("hola");
        articulo.setStock(10);
        articulo.setPrecioU(10);
        articulo.setFechModi(new Date(1));
        articulo.setProveedor(10);

        Boolean articuloModificado = articuloService.modificarArticulo(articulo);
        assertFalse(articuloModificado);
    }
}
