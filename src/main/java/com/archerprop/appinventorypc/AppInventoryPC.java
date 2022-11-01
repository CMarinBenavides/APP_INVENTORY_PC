/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.archerprop.appinventorypc;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Aschente
 */
@SpringBootApplication
@Log4j2
public class AppInventoryPC {

    public static void main(String[] args) {
        SpringApplication.run(AppInventoryPC.class, args);
    }
}
