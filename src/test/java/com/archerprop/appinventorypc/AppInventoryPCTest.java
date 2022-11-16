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

import com.archerprop.appinventorypc.controller.AppController;
import com.archerprop.appinventorypc.entidad.Articulos;
import com.archerprop.appinventorypc.entidad.Usuarios;
import com.archerprop.appinventorypc.services.ArticuloService;
import com.archerprop.appinventorypc.services.InventarioService;
import com.archerprop.appinventorypc.services.UsuarioService;
import com.github.javafaker.Faker;
import java.sql.Date;
import java.sql.Timestamp;

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

    @Autowired
    private UsuarioService usuarioService;

    @Test
    public void testearServicioCrearArticulo() {
        Faker faker = new Faker();
        Articulos articulo = new Articulos();
        articulo.setNombre(faker.name().fullName());
        articulo.setSerial("h");
        articulo.setStock(10);
        articulo.setPrecioU(10);
        articulo.setFechModi(new Timestamp(new Date(0).getTime()));
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
        articulo.setFechModi(new Timestamp(new Date(0).getTime()));
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
        articulo.setFechModi(new Timestamp(new Date(0).getTime()));
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
        articulo.setFechModi(new Timestamp(new Date(0).getTime()));
        articulo.setProveedor(10);

        Boolean articuloCreado = articuloService.crearArticulo(articulo);
        assertTrue(articuloCreado);

        articulo.setNombre(faker.name().fullName());
        articulo.setSerial("hola");
        articulo.setStock(10);
        articulo.setPrecioU(10);
        articulo.setFechModi(new Timestamp(new Date(0).getTime()));
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
        articulo.setFechModi(new Timestamp(new Date(0).getTime()));
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
        articulo.setFechModi(new Timestamp(new Date(0).getTime()));
        articulo.setProveedor(10);

        Boolean articuloCreado = articuloService.crearArticulo(articulo);
        assertTrue(articuloCreado);

        articulo.setNombre(faker.name().fullName());
        articulo.setSerial("hola");
        articulo.setStock(-10);
        articulo.setPrecioU(-10);
        articulo.setFechModi(new Timestamp(new Date(0).getTime()));
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
        articulo.setFechModi(new Timestamp(new Date(0).getTime()));
        articulo.setProveedor(10);

        Boolean articuloModificado = articuloService.modificarArticulo(articulo);
        assertFalse(articuloModificado);
    }

    @Test
    public void testearServicioEliminarArticulo() {
        Faker faker = new Faker();
        Articulos articulo = new Articulos();
        articulo.setNombre(faker.name().fullName());
        articulo.setSerial("hola");
        articulo.setStock(10);
        articulo.setPrecioU(10);
        articulo.setFechModi(new Timestamp(new Date(0).getTime()));
        articulo.setProveedor(10);

        Boolean articuloCreado = articuloService.crearArticulo(articulo);
        assertTrue(articuloCreado);

        Boolean articuloEliminado = articuloService.eliminarArticulo(articulo);
        assertTrue(articuloEliminado);
    }

    @Test
    public void testearServicioEliminarArticuloNull() {
        Articulos articulo = null;
        Boolean articuloEliminado = articuloService.eliminarArticulo(articulo);
        assertFalse(articuloEliminado);
    }

    @Test
    public void testearServicioEliminarArticuloNoExiste() {
        Faker faker = new Faker();
        Articulos articulo = new Articulos();
        articulo.setNombre(faker.name().fullName());
        articulo.setSerial("hola");
        articulo.setStock(10);
        articulo.setPrecioU(10);
        articulo.setFechModi(new Timestamp(new Date(0).getTime()));
        articulo.setProveedor(10);

        Boolean articuloEliminado = articuloService.eliminarArticulo(articulo);
        assertFalse(articuloEliminado);
    }

    @Test
    public void testearServicioListarArticulos() {
        Faker faker = new Faker();
        Articulos articulo = new Articulos();
        articulo.setNombre(faker.name().fullName());
        articulo.setSerial("hola");
        articulo.setStock(10);
        articulo.setPrecioU(10);
        articulo.setFechModi(new Timestamp(new Date(0).getTime()));
        articulo.setProveedor(10);

        Boolean articuloCreado = articuloService.crearArticulo(articulo);
        assertTrue(articuloCreado);

        List<Articulos> articulos = articuloService.listarArticulos();
        assertNotNull(articulos);
        assertFalse(articulos.isEmpty());
    }

    @Test
    public void testearServicioListarArticulosVacio() {
        List<Articulos> articulos = articuloService.listarArticulos();
        assertNotNull(articulos);
        assertTrue(articulos.isEmpty());
    }

    @Test
    public void testearServicioListarArticulosPorNombre() {
        Faker faker = new Faker();
        Articulos articulo = new Articulos();
        articulo.setNombre(faker.name().fullName());
        articulo.setSerial("hola");
        articulo.setStock(10);
        articulo.setPrecioU(10);
        articulo.setFechModi(new Timestamp(new Date(0).getTime()));
        articulo.setProveedor(10);

        Boolean articuloCreado = articuloService.crearArticulo(articulo);
        assertTrue(articuloCreado);

        List<Articulos> articulos = articuloService.listarArticulosPorNombre(articulo.getNombre());
        assertNotNull(articulos);
        assertFalse(articulos.isEmpty());
    }

    @Test
    public void testearServicioCrearUsuario() {
        Faker faker = new Faker();
        Usuarios usuario = new Usuarios();
        usuario.setCedula(123456789);
        usuario.setNombre(faker.name().name());
        usuario.setApellido(faker.name().lastName());
        usuario.setCorreo("provtest@test.com");
        usuario.setClave("1234");
        usuario.setTipoP(1);
        usuario.setTipoE(0);

        Boolean usuarioCreado = usuarioService.crearUsuario(usuario);
        assertTrue(usuarioCreado);
    }

    @Test
    public void testearServicioAutenticar() {
        Faker faker = new Faker();
        Usuarios usuario = new Usuarios();
        usuario.setCedula(123456789);
        usuario.setNombre(faker.name().name());
        usuario.setApellido(faker.name().lastName());
        usuario.setCorreo("provtest@test.com");
        usuario.setClave("1234");
        usuario.setTipoP(1);
        usuario.setTipoE(0);

        Boolean usuarioCreado = usuarioService.crearUsuario(usuario);
        assertTrue(usuarioCreado);

        Boolean usuarioAutenticado = usuarioService.autenticar(usuario);
        assertTrue(usuarioAutenticado);
    }

    @Test
    public void testearServicioObtenerUsuario() {
        Faker faker = new Faker();
        Usuarios usuario = new Usuarios();
        usuario.setCedula(1000621475);
        usuario.setNombre(faker.name().name());
        usuario.setApellido(faker.name().lastName());
        usuario.setCorreo("hilario.pacocha@yahoo.com");
        usuario.setClave("1234");
        usuario.setTipoP(1);
        usuario.setTipoE(0);
        Usuarios usuarioObtenido = usuarioService.obtenerUsuario(usuario.getCorreo());
        assertNotNull(usuarioObtenido);
    }
}
