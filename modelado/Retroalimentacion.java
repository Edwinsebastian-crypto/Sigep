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

public class Retroalimentacion {
    private String idRetroalimentacion;
    private String comentario;
    private Date   fecha;
    private String idBitacora;
    private String numDocTutor;   // FK → TutorAcademico (puede ser null)
    private String numDocAsesor;  // FK → AsesorPedagogico (puede ser null)

    public Retroalimentacion() {}

    public Retroalimentacion(String idRetroalimentacion, String comentario, Date fecha,
                             String idBitacora, String numDocTutor, String numDocAsesor) {
        this.idRetroalimentacion = idRetroalimentacion;
        this.comentario          = comentario;
        this.fecha               = fecha;
        this.idBitacora          = idBitacora;
        this.numDocTutor         = numDocTutor;
        this.numDocAsesor        = numDocAsesor;
    }

    public String getIdRetroalimentacion() { return idRetroalimentacion; }
    public void   setIdRetroalimentacion(String v) { this.idRetroalimentacion = v; }
    public String getComentario() { return comentario; }
    public void   setComentario(String v) { this.comentario = v; }
    public Date   getFecha() { return fecha; }
    public void   setFecha(Date v) { this.fecha = v; }
    public String getIdBitacora() { return idBitacora; }
    public void   setIdBitacora(String v) { this.idBitacora = v; }
    public String getNumDocTutor() { return numDocTutor; }
    public void   setNumDocTutor(String v) { this.numDocTutor = v; }
    public String getNumDocAsesor() { return numDocAsesor; }
    public void   setNumDocAsesor(String v) { this.numDocAsesor = v; }

    @Override
    public String toString() {
        return "Retroalimentacion{id='" + idRetroalimentacion + "', bitacora='" + idBitacora + "'}";
    }
}
