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

public class Bitacora {
    private String idBitacora;
    private Date   fechaCreacion;
    private double notaFinal;
    private String observacionFinal;
    private String numDocumento;   // FK → Estudiante
    private String idPractica;    // FK → Practica

    public Bitacora() {}

    public Bitacora(String idBitacora, Date fechaCreacion, double notaFinal,
                    String observacionFinal, String numDocumento, String idPractica) {
        this.idBitacora       = idBitacora;
        this.fechaCreacion    = fechaCreacion;
        this.notaFinal        = notaFinal;
        this.observacionFinal = observacionFinal;
        this.numDocumento     = numDocumento;
        this.idPractica       = idPractica;
    }

    public String getIdBitacora()  { return idBitacora; }
    public void   setIdBitacora(String v)  { this.idBitacora = v; }
    public Date   getFechaCreacion() { return fechaCreacion; }
    public void   setFechaCreacion(Date v) { this.fechaCreacion = v; }
    public double getNotaFinal() { return notaFinal; }
    public void   setNotaFinal(double v) { this.notaFinal = v; }
    public String getObservacionFinal() { return observacionFinal; }
    public void   setObservacionFinal(String v) { this.observacionFinal = v; }
    public String getNumDocumento()  { return numDocumento; }
    public void   setNumDocumento(String v)  { this.numDocumento = v; }
    public String getIdPractica() { return idPractica; }
    public void   setIdPractica(String v) { this.idPractica = v; }

    @Override
    public String toString() {
        return "Bitacora{id='" + idBitacora + "', nota=" + notaFinal + ", practica='" + idPractica + "'}";
    }
}
