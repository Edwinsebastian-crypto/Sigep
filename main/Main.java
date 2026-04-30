/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect.main;

/**
 *
 * @author EdwinPruebas
 */



import com.mycompany.proyect.vista.SplashWindow;

import javax.swing.*;

/**
 * ╔══════════════════════════════════════════════════════════════════╗
 * ║           SIGEP — Sistema de Gestión de Prácticas              ║
 * ║           Pedagógicas                                           ║
 * ╠══════════════════════════════════════════════════════════════════╣
 * ║  Arquitectura MVC                                               ║
 * ║  • com.mycompany.proyect.main        → Punto de entrada (este archivo)  ║
 * ║  • com.mycompany.proyect.vista       → Vistas Swing (SIGEP.zip sin modificar)║
 * ║  • com.mycompany.proyect.modelado    → Conexión + DAOs + Clases de dominio ║
 * ║  • com.mycompany.proyect.controlador → Lógica CRUD que une vista ↔ modelo  ║
 * ╠══════════════════════════════════════════════════════════════════╣
 * ║  Requisitos                                                      ║
 * ║  • Oracle XE corriendo en localhost:1521, SID=XE                ║
 * ║  • Usuario: PROYECTOSIGEP  /  Password: PROYECTOSIGEP           ║
 * ║  • ojdbc11.jar en el classpath (dependencia Maven ojdbc11)      ║
 * ║  • Base de datos restaurada desde PROYECTOSIGEPBD.DMP           ║
 * ╚══════════════════════════════════════════════════════════════════╝
 */
public class Main {

    public static void main(String[] args) {

        // Aplicar el Look & Feel del sistema operativo para mejor apariencia
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Si falla, continúa con el Look & Feel por defecto de Java
        }

        // Todo el trabajo con Swing debe hacerse en el Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {

            // Opción A (con Splash Screen): muestra 3 s el logo y luego abre el login
            SplashWindow splash = new SplashWindow();
            splash.setVisible(true);

            /*
             * Opción B (sin Splash Screen): descomenta estas líneas
             * y comenta las dos de arriba si quieres ir directo al login.
             *
             * LoginWindow login = new LoginWindow();
             * new LoginControlador(login);
             * login.setVisible(true);
             */
        });
    }
}
