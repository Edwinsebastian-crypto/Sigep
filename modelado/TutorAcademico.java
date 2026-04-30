/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect.modelado;



/**
 *
 * @author EdwinPruebas
 */
   
public class TutorAcademico {
    private String numDocumento;
    private String tipoDocumento;
    private String nombre;
    private String apellido;
    private String correoInst;
    private String contrasena;
    private String estado;
    private String idPrograma;

    public TutorAcademico() {}

    public TutorAcademico(String numDocumento, String tipoDocumento, String nombre,
                          String apellido, String correoInst, String contrasena,
                          String estado, String idPrograma) {
        this.numDocumento  = numDocumento;
        this.tipoDocumento = tipoDocumento;
        this.nombre        = nombre;
        this.apellido      = apellido;
        this.correoInst    = correoInst;
        this.contrasena    = contrasena;
        this.estado        = estado;
        this.idPrograma    = idPrograma;
    }

    public String getNumDocumento()  { return numDocumento; }
    public void   setNumDocumento(String v)  { this.numDocumento = v; }
    public String getTipoDocumento() { return tipoDocumento; }
    public void   setTipoDocumento(String v) { this.tipoDocumento = v; }
    public String getNombre()  { return nombre; }
    public void   setNombre(String v)  { this.nombre = v; }
    public String getApellido() { return apellido; }
    public void   setApellido(String v) { this.apellido = v; }
    public String getCorreoInst() { return correoInst; }
    public void   setCorreoInst(String v) { this.correoInst = v; }
    public String getContrasena() { return contrasena; }
    public void   setContrasena(String v) { this.contrasena = v; }
    public String getEstado() { return estado; }
    public void   setEstado(String v) { this.estado = v; }
    public String getIdPrograma() { return idPrograma; }
    public void   setIdPrograma(String v) { this.idPrograma = v; }

    @Override
    public String toString() {
        return "TutorAcademico{doc='" + numDocumento + "', nombre='" + nombre + " " + apellido + "'}";
    }
}
