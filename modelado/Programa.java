/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect.modelado;


/**
 *
 * @author EdwinPruebas
 */


public class Programa {
    private String idPrograma;
    private String nombre;
    private String facultad;

    public Programa() {}

    public Programa(String idPrograma, String nombre, String facultad) {
        this.idPrograma = idPrograma;
        this.nombre     = nombre;
        this.facultad   = facultad;
    }

    public String getIdPrograma() { return idPrograma; }
    public void   setIdPrograma(String idPrograma) { this.idPrograma = idPrograma; }

    public String getNombre() { return nombre; }
    public void   setNombre(String nombre) { this.nombre = nombre; }

    public String getFacultad() { return facultad; }
    public void   setFacultad(String facultad) { this.facultad = facultad; }

    @Override
    public String toString() {
        return "Programa{id='" + idPrograma + "', nombre='" + nombre + "', facultad='" + facultad + "'}";
    }
}
