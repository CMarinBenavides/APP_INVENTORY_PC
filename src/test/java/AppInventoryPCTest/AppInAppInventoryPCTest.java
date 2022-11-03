/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AppInventoryPCTest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;
import com.archerprop.appinventorypc.entidad.Articulos;
import com.archerprop.appinventorypc.services.ArticuloService;
import com.github.javafaker.Faker;

/**
 *
 * @author Aschente
 */
@SpringBootTest
@ActiveProfiles("test")
public class AppInAppInventoryPCTest {

    @Autowired
    private ArticuloService articuloService;

    @Test
    public void testearServicio() {
        Faker faker = new Faker();
        String serial = faker.code().isbn10();
        List<Articulos> articulos = articuloService.leerArticulosSimilaresS(serial);
        assertTrue(articulos.size() > 0);
    }
}
