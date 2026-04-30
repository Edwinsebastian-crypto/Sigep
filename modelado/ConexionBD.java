package com.mycompany.proyect.modelado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase Singleton para gestionar la conexión a Oracle DB.
 * Proyecto SIGEP
 */
public class ConexionBD {

    // ── Parámetros de conexión ──────────────────────────────────────────────
    private static final String HOST      = "localhost";
    private static final String PUERTO    = "1521";
    private static final String SID       = "XE";        // Cambia si tu SID es distinto
    private static final String USUARIO   = "PROYECTOSIGEP";
    private static final String PASSWORD  = "PROYECTOSIGEP";

    private static final String URL =
            "jdbc:oracle:thin:@" + HOST + ":" + PUERTO + ":" + SID;

    // ── Singleton ───────────────────────────────────────────────────────────
    private static ConexionBD instancia;
    private Connection conexion;

    private ConexionBD() {}

    /** Devuelve la única instancia de ConexionBD (thread-safe). */
    public static synchronized ConexionBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    // ── Métodos públicos ────────────────────────────────────────────────────

    /**
     * Abre (o reutiliza) la conexión a Oracle.
     * Si la conexión estaba rota, la descarta e intenta de nuevo.
     * @return Connection activa, o null si falla.
     */
    public Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = null; // descarta referencia rota antes de reconectar
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                System.out.println("✔ Conexión a Oracle establecida.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("✘ Driver Oracle no encontrado. Agrega ojdbc.jar al proyecto.");
            conexion = null;
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("✘ Error SQL al conectar: " + e.getMessage());
            conexion = null;
            e.printStackTrace();
        }
        return conexion;
    }

    /** Cierra la conexión de forma segura y permite reconectar en el siguiente getConexion(). */
    public void cerrar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("✔ Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("✘ Error al cerrar conexión: " + e.getMessage());
        } finally {
            conexion = null; // permite reconectar tras cerrar
        }
    }
}
