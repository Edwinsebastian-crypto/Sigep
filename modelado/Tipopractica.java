/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect.modelado;


/**
 *
 * @author EdwinPruebas
 */


public class Tipopractica {
    private String idTipopractica;
    private String nombre;
    private int    numSemestre;
    private int    horasRequeridas;

    public Tipopractica() {}

    public Tipopractica(String idTipopractica, String nombre, int numSemestre, int horasRequeridas) {
        this.idTipopractica  = idTipopractica;
        this.nombre          = nombre;
        this.numSemestre     = numSemestre;
        this.horasRequeridas = horasRequeridas;
    }

    public String getIdTipopractica()  { return idTipopractica; }
    public void   setIdTipopractica(String v)  { this.idTipopractica = v; }
    public String getNombre()  { return nombre; }
    public void   setNombre(String v)  { this.nombre = v; }
    public int    getNumSemestre() { return numSemestre; }
    public void   setNumSemestre(int v) { this.numSemestre = v; }
    public int    getHorasRequeridas() { return horasRequeridas; }
    public void   setHorasRequeridas(int v) { this.horasRequeridas = v; }

    @Override
    public String toString() {
        return "Tipopractica{id='" + idTipopractica + "', nombre='" + nombre + "', semestre=" + numSemestre + "}";
    }
}
