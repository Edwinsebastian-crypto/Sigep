
package com.mycompany.proyect.controlador;

/**
 *
 * @author EdwinPruebas
 */

import com.mycompany.proyect.modelado.AsesorPedagogico;
import com.mycompany.proyect.modelado.Estudiante;
import com.mycompany.proyect.modelado.TutorAcademico;
import com.mycompany.proyect.modelado.ConexionBD;
import com.mycompany.proyect.modelado.AsesorPedagogicoDAO;
import com.mycompany.proyect.modelado.EstudianteDAO;
import com.mycompany.proyect.modelado.TutorAcademicoDAO;
import com.mycompany.proyect.vista.LoginWindow;
import com.mycompany.proyect.vista.PortalAsesorPedagogico;
import com.mycompany.proyect.vista.PortalTutorAcademico;
import com.mycompany.proyect.vista.StudentDashboard;

import javax.swing.*;
import java.sql.Connection;

/**
 * Controlador de Login.
 * Conecta el botón "Ingresar" de LoginWindow con los DAOs de autenticación.
 * Según el rol seleccionado abre el portal correspondiente.
 *
 * Flujo:
 * SplashWindow → new LoginWindow() → new LoginControlador(login) →
 * login.setVisible(true)
 */
public class LoginControlador {

    private LoginWindow vista;

    private EstudianteDAO estudianteDAO;
    private TutorAcademicoDAO tutorDAO;
    private AsesorPedagogicoDAO asesorDAO;

    public LoginControlador(LoginWindow vista) {
        this.vista = vista;
        if (vista == null)
            return; // protección para uso en logout

        Connection conn = ConexionBD.getInstancia().getConexion();

        if (conn == null) {
            JOptionPane.showMessageDialog(vista,
                    "No se pudo conectar a la base de datos.\n" +
                            "Verifique que Oracle XE esté activo en localhost:1521\n" +
                            "Usuario: PROYECTOSIGEP | SID: XE\n" +
                            "y que el driver ojdbc11.jar esté en el classpath.",
                    "Error de conexión", JOptionPane.ERROR_MESSAGE);
            return; // ← CRÍTICO: evita instanciar DAOs con conn=null
        }

        this.estudianteDAO = new EstudianteDAO(conn);
        this.tutorDAO = new TutorAcademicoDAO(conn);
        this.asesorDAO = new AsesorPedagogicoDAO(conn);

        inicializarEventos();
    }

    // ── Eventos ──────────────────────────────────────────────────────────────
    private void inicializarEventos() {
        vista.getBtnIngresar().addActionListener(e -> procesarLogin());
    }

    // ── Lógica de autenticación ───────────────────────────────────────────────
    private void procesarLogin() {
        String correo = vista.getCorreo();
        String contrasena = vista.getContrasena();
        String rol = vista.getRolSeleccionado();

        if (correo.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(vista,
                    "Por favor ingresa correo y contraseña.",
                    "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (rol.isEmpty() || rol.equalsIgnoreCase("Seleccione su rol")) {
            JOptionPane.showMessageDialog(vista,
                    "Selecciona un rol válido.",
                    "Rol inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        switch (rol) {
            case "Estudiante":
                loginEstudiante(correo, contrasena);
                break;
            case "Tutor académico":
            case "Tutor Académico":
                loginTutor(correo, contrasena);
                break;
            case "Asesor pedagógico":
            case "Asesor Pedagógico":
                loginAsesor(correo, contrasena);
                break;
            default:
                JOptionPane.showMessageDialog(vista,
                        "Rol no reconocido: " + rol,
                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loginEstudiante(String correo, String contrasena) {
        Estudiante estudiante = estudianteDAO.login(correo, contrasena);
        if (estudiante != null) {
            vista.dispose();
            new StudentDashboard(estudiante).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(vista,
                    "Credenciales incorrectas para Estudiante.",
                    "Acceso denegado", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loginTutor(String correo, String contrasena) {
        TutorAcademico tutor = tutorDAO.login(correo, contrasena);
        if (tutor != null) {
            vista.dispose();
            new PortalTutorAcademico(tutor).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(vista,
                    "Credenciales incorrectas para Tutor Académico.",
                    "Acceso denegado", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loginAsesor(String correo, String contrasena) {
        AsesorPedagogico asesor = asesorDAO.login(correo, contrasena);
        if (asesor != null) {
            vista.dispose();
            new PortalAsesorPedagogico(asesor).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(vista,
                    "Credenciales incorrectas para Asesor Pedagógico.",
                    "Acceso denegado", JOptionPane.ERROR_MESSAGE);
        }
    }
}
