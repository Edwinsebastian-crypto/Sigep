/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect.modelado;

/**
 *
 * @author EdwinPruebas
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la tabla PRACTICA.
 */
public class PracticaDAO {

    private Connection conn;

    public PracticaDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertar(Practica p) {
        String sql = "INSERT INTO Practica (Idpractica, Fecha, Entidad, Estado, " +
                     "Numdocumento, IdTipopractica, Idprograma) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getIdPractica());
            ps.setDate(2, p.getFecha());
            ps.setString(3, p.getEntidad());
            ps.setString(4, p.getEstado());
            ps.setString(5, p.getNumDocumento());
            ps.setString(6, p.getIdTipopractica());
            ps.setString(7, p.getIdPrograma());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar Practica: " + e.getMessage());
            return false;
        }
    }

    public Practica buscarPorId(String idPractica) {
        String sql = "SELECT * FROM Practica WHERE Idpractica = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idPractica);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        } catch (SQLException e) {
            System.err.println("Error al buscar Practica: " + e.getMessage());
        }
        return null;
    }

    /** Lista las prácticas de un estudiante. */
    public List<Practica> listarPorEstudiante(String numDocumento) {
        List<Practica> lista = new ArrayList<>();
        String sql = "SELECT * FROM Practica WHERE Numdocumento = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, numDocumento);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) {
            System.err.println("Error al listar prácticas por estudiante: " + e.getMessage());
        }
        return lista;
    }

    /** Lista las prácticas de un programa. */
    public List<Practica> listarPorPrograma(String idPrograma) {
        List<Practica> lista = new ArrayList<>();
        String sql = "SELECT * FROM Practica WHERE Idprograma = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idPrograma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) {
            System.err.println("Error al listar prácticas por programa: " + e.getMessage());
        }
        return lista;
    }

    public List<Practica> listarTodas() {
        List<Practica> lista = new ArrayList<>();
        String sql = "SELECT * FROM Practica";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) {
            System.err.println("Error al listar Practicas: " + e.getMessage());
        }
        return lista;
    }

    public boolean cambiarEstado(String idPractica, String nuevoEstado) {
        String sql = "UPDATE Practica SET Estado = ? WHERE Idpractica = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado);
            ps.setString(2, idPractica);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al cambiar estado Practica: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Practica p) {
        String sql = "UPDATE Practica SET Fecha=?, Entidad=?, Estado=?, Numdocumento=?, " +
                     "IdTipopractica=?, Idprograma=? WHERE Idpractica=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, p.getFecha());
            ps.setString(2, p.getEntidad());
            ps.setString(3, p.getEstado());
            ps.setString(4, p.getNumDocumento());
            ps.setString(5, p.getIdTipopractica());
            ps.setString(6, p.getIdPrograma());
            ps.setString(7, p.getIdPractica());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar Practica: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(String idPractica) {
        String sql = "DELETE FROM Practica WHERE Idpractica = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idPractica);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar Practica: " + e.getMessage());
            return false;
        }
    }

    private Practica mapear(ResultSet rs) throws SQLException {
        return new Practica(
            rs.getString("Idpractica"),
            rs.getDate("Fecha"),
            rs.getString("Entidad"),
            rs.getString("Estado"),
            rs.getString("Numdocumento"),
            rs.getString("IdTipopractica"),
            rs.getString("Idprograma")
        );
    }
}
