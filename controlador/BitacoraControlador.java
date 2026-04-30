/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect.controlador;

/**
 *
 * @author EdwinPruebas
 */

import com.mycompany.proyect.modelado.Bitacora;
import com.mycompany.proyect.modelado.Retroalimentacion;
import com.mycompany.proyect.modelado.ConexionBD;
import com.mycompany.proyect.modelado.BitacoraDAO;
import com.mycompany.proyect.modelado.RetroalimentacionDAO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

/**
 * Controlador de Bitácora y Retroalimentación.
 * Gestiona la creación de bitácoras, cierre con nota/observación,
 * y el registro de retroalimentaciones por tutor o asesor.
 */
public class BitacoraControlador {

    private BitacoraDAO            bitacoraDAO;
    private RetroalimentacionDAO   retroDAO;

    public BitacoraControlador() {
        Connection conn = ConexionBD.getInstancia().getConexion();
        this.bitacoraDAO = new BitacoraDAO(conn);
        this.retroDAO    = new RetroalimentacionDAO(conn);
    }

    // ── BITÁCORA: CREATE ──────────────────────────────────────────────────────
    /**
     * Crea una nueva bitácora asociada a una práctica y un estudiante.
     * Genera automáticamente el ID de bitácora.
     */
    public boolean crearBitacora(String numDocEstudiante, String idPractica) {
        // Verificar que no exista ya una bitácora para esa práctica
        if (bitacoraDAO.buscarPorPractica(idPractica) != null) {
            mostrarError("Ya existe una bitácora para esta práctica.");
            return false;
        }
        String idBitacora = "BIT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Date hoy = new Date(System.currentTimeMillis());
        Bitacora b = new Bitacora(idBitacora, hoy, 0, null, numDocEstudiante, idPractica);
        boolean ok = bitacoraDAO.insertar(b);
        if (ok) mostrarInfo("Bitácora creada con ID: " + idBitacora);
        else    mostrarError("No se pudo crear la bitácora.");
        return ok;
    }

    // ── BITÁCORA: READ ────────────────────────────────────────────────────────
    public Bitacora buscarPorPractica(String idPractica) {
        return bitacoraDAO.buscarPorPractica(idPractica);
    }

    public List<Bitacora> listarPorEstudiante(String numDocumento) {
        return bitacoraDAO.listarPorEstudiante(numDocumento);
    }

    // ── BITÁCORA: UPDATE (cerrar con nota y observación) ─────────────────────
    /**
     * El Tutor Académico cierra la bitácora asignando nota final y observación.
     */
    public boolean cerrarBitacora(String idBitacora, String notaTexto, String observacion) {
        if (notaTexto.isEmpty() || observacion.isEmpty()) {
            mostrarError("Nota y observación son obligatorias para cerrar la bitácora.");
            return false;
        }
        double nota;
        try {
            nota = Double.parseDouble(notaTexto);
            if (nota < 0 || nota > 5) {
                mostrarError("La nota debe estar entre 0 y 5.");
                return false;
            }
        } catch (NumberFormatException ex) {
            mostrarError("La nota debe ser un número válido (ej: 4.5).");
            return false;
        }
        boolean ok = bitacoraDAO.cerrarBitacora(idBitacora, nota, observacion);
        if (ok) mostrarInfo("Bitácora cerrada con nota: " + nota);
        else    mostrarError("No se pudo cerrar la bitácora.");
        return ok;
    }

    // ── BITÁCORA: DELETE ──────────────────────────────────────────────────────
    public boolean eliminarBitacora(String idBitacora, JComponent parent) {
        int confirm = JOptionPane.showConfirmDialog(
            parent,
            "¿Eliminar la bitácora " + idBitacora + " y todas sus retroalimentaciones?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        if (confirm != JOptionPane.YES_OPTION) return false;
        boolean ok = bitacoraDAO.eliminar(idBitacora);
        if (ok) mostrarInfo("Bitácora eliminada.");
        else    mostrarError("No se pudo eliminar la bitácora.");
        return ok;
    }

    // ── RETROALIMENTACIÓN: CREATE ─────────────────────────────────────────────
    /**
     * Registra una retroalimentación de un Tutor sobre una bitácora.
     */
    public boolean agregarRetroalimentacionTutor(String idBitacora, String comentario, String numDocTutor) {
        return agregarRetroalimentacion(idBitacora, comentario, numDocTutor, null);
    }

    /**
     * Registra una retroalimentación de un Asesor sobre una bitácora.
     */
    public boolean agregarRetroalimentacionAsesor(String idBitacora, String comentario, String numDocAsesor) {
        return agregarRetroalimentacion(idBitacora, comentario, null, numDocAsesor);
    }

    private boolean agregarRetroalimentacion(String idBitacora, String comentario,
                                             String numDocTutor, String numDocAsesor) {
        if (comentario.isEmpty()) {
            mostrarError("El comentario no puede estar vacío.");
            return false;
        }
        String id = "RET-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Date hoy  = new Date(System.currentTimeMillis());
        Retroalimentacion r = new Retroalimentacion(id, comentario, hoy, idBitacora, numDocTutor, numDocAsesor);
        boolean ok = retroDAO.insertar(r);
        if (ok) mostrarInfo("Retroalimentación registrada.");
        else    mostrarError("No se pudo registrar la retroalimentación.");
        return ok;
    }

    // ── RETROALIMENTACIÓN: READ ───────────────────────────────────────────────
    public List<Retroalimentacion> listarRetroalimentaciones(String idBitacora) {
        return retroDAO.listarPorBitacora(idBitacora);
    }

    // ── RETROALIMENTACIÓN: DELETE ─────────────────────────────────────────────
    public boolean eliminarRetroalimentacion(String idRetro, JComponent parent) {
        int confirm = JOptionPane.showConfirmDialog(
            parent, "¿Eliminar esta retroalimentación?",
            "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return false;
        return retroDAO.eliminar(idRetro);
    }

    // ── Helpers ───────────────────────────────────────────────────────────────
    private void mostrarInfo(String msg) {
        JOptionPane.showMessageDialog(null, msg, "SIGEP", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
