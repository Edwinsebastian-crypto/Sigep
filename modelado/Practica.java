/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect.modelado;

/**
 *
 * @author EdwinPruebas
 */


import java.sql.Date;

public class Practica {
    private String idPractica;
    private Date   fecha;
    private String entidad;
    private String estado;
    private String numDocumento;      // FK → Estudiante
    private String idTipopractica;    // FK → Tipopractica
    private String idPrograma;        // FK → Programa

    public Practica() {}

    public Practica(String idPractica, Date fecha, String entidad, String estado,
                    String numDocumento, String idTipopractica, String idPrograma) {
        this.idPractica     = idPractica;
        this.fecha          = fecha;
        this.entidad        = entidad;
        this.estado         = estado;
        this.numDocumento   = numDocumento;
        this.idTipopractica = idTipopractica;
        this.idPrograma     = idPrograma;
    }

    public String getIdPractica()  { return idPractica; }
    public void   setIdPractica(String v)  { this.idPractica = v; }
    public Date   getFecha() { return fecha; }
    public void   setFecha(Date v) { this.fecha = v; }
    public String getEntidad() { return entidad; }
    public void   setEntidad(String v) { this.entidad = v; }
    public String getEstado() { return estado; }
    public void   setEstado(String v) { this.estado = v; }
    public String getNumDocumento()  { return numDocumento; }
    public void   setNumDocumento(String v)  { this.numDocumento = v; }
    public String getIdTipopractica() { return idTipopractica; }
    public void   setIdTipopractica(String v) { this.idTipopractica = v; }
    public String getIdPrograma() { return idPrograma; }
    public void   setIdPrograma(String v) { this.idPrograma = v; }

    @Override
    public String toString() {
        return "Practica{id='" + idPractica + "', entidad='" + entidad + "', estado='" + estado + "'}";
    }
}
