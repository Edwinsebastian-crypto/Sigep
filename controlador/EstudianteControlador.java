/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect.controlador;

/**
 *
 * @author EdwinPruebas
 */

import com.mycompany.proyect.modelado.Estudiante;
import com.mycompany.proyect.modelado.ConexionBD;
import com.mycompany.proyect.modelado.EstudianteDAO;

import javax.swing.*;
import java.sql.Connection;
import java.util.List;

/**
 * Controlador de Estudiante.
 * Provee los métodos CRUD que los paneles de vista invocan
 * cuando el usuario interactúa con los botones.
 *
 * Uso desde la vista:
 *   EstudianteControlador ctrl = new EstudianteControlador();
 *   ctrl.registrar(...);
 *   ctrl.actualizar(...);
 *   etc.
 */
public class EstudianteControlador {

    private EstudianteDAO dao;

    public EstudianteControlador() {
        Connection conn = ConexionBD.getInstancia().getConexion();
        this.dao = new EstudianteDAO(conn);
    }

    // ── CREATE ────────────────────────────────────────────────────────────────
    /**
     * Registra un nuevo estudiante en la BD.
     * Valida que los campos obligatorios no estén vacíos.
     * @return true si se insertó correctamente.
     */
    public boolean registrar(String numDoc, String tipoDoc, String nombre, String apellido,
                             String correo, String contrasena, String estado, String idPrograma) {
        if (numDoc.isEmpty() || nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            mostrarError("Todos los campos obligatorios deben estar completos.");
            return false;
        }
        // Verificar que no exista ya
        if (dao.buscarPorDocumento(numDoc) != null) {
            mostrarError("Ya existe un estudiante con ese número de documento.");
            return false;
        }
        Estudiante e = new Estudiante(numDoc, tipoDoc, nombre, apellido, correo, contrasena, estado, idPrograma);
        boolean ok = dao.insertar(e);
        if (ok) mostrarInfo("Estudiante registrado exitosamente.");
        else    mostrarError("No se pudo registrar el estudiante. Verifica los datos.");
        return ok;
    }

    // ── READ ──────────────────────────────────────────────────────────────────
    /** Busca un estudiante por número de documento. */
    public Estudiante buscar(String numDoc) {
        Estudiante e = dao.buscarPorDocumento(numDoc);
        if (e == null) mostrarError("No se encontró ningún estudiante con ese documento.");
        return e;
    }

    /** Retorna todos los estudiantes registrados. */
    public List<Estudiante> listarTodos() {
        return dao.listarTodos();
    }

    /** Retorna los estudiantes de un programa académico. */
    public List<Estudiante> listarPorPrograma(String idPrograma) {
        return dao.listarPorPrograma(idPrograma);
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    /**
     * Actualiza los datos de un estudiante existente.
     * @return true si se actualizó correctamente.
     */
    public boolean actualizar(String numDoc, String tipoDoc, String nombre, String apellido,
                              String correo, String contrasena, String estado, String idPrograma) {
        if (nombre.isEmpty() || correo.isEmpty()) {
            mostrarError("Nombre y correo no pueden estar vacíos.");
            return false;
        }
        Estudiante e = new Estudiante(numDoc, tipoDoc, nombre, apellido, correo, contrasena, estado, idPrograma);
        boolean ok = dao.actualizar(e);
        if (ok) mostrarInfo("Estudiante actualizado correctamente.");
        else    mostrarError("No se pudo actualizar. Verifica que el estudiante exista.");
        return ok;
    }

    /** Cambia el estado (Activo / Inactivo) de un estudiante. */
    public boolean cambiarEstado(String numDoc, String nuevoEstado) {
        boolean ok = dao.cambiarEstado(numDoc, nuevoEstado);
        if (ok) mostrarInfo("Estado actualizado a: " + nuevoEstado);
        else    mostrarError("No se pudo cambiar el estado.");
        return ok;
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    /**
     * Elimina un estudiante previa confirmación del usuario.
     * @return true si se eliminó.
     */
    public boolean eliminar(String numDoc, JComponent parent) {
        int confirm = JOptionPane.showConfirmDialog(
            parent,
            "¿Estás seguro de eliminar al estudiante con documento " + numDoc + "?\nEsta acción no se puede deshacer.",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        if (confirm != JOptionPane.YES_OPTION) return false;

        boolean ok = dao.eliminar(numDoc);
        if (ok) mostrarInfo("Estudiante eliminado.");
        else    mostrarError("No se pudo eliminar. Puede tener registros asociados.");
        return ok;
    }

    // ── Helpers ───────────────────────────────────────────────────────────────
    private void mostrarInfo(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "SIGEP", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
