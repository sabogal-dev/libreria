/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.poo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO (Data Access Object) para la entidad Libro.
 * Encapsula todas las operaciones de base de datos relacionadas con libros.
 * @author emilio
 */
public class LibroDAO {

    /**
     * Obtiene todos los libros de la base de datos.
     * @return Lista de objetos Libro
     * @throws SQLException si hay error de BD
     */
    public List<Libro> obtenerTodos() throws SQLException {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT id, titulo, genero, calificacion, estado FROM libros ORDER BY id";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Libro l = new Libro();
                l.setId(rs.getInt("id"));
                l.setTitulo(rs.getString("titulo"));
                l.setGenero(rs.getString("genero"));
                l.setCalificacion(rs.getDouble("calificacion"));
                l.setEstado(rs.getString("estado"));
                libros.add(l);
            }
        }
        return libros;
    }

    /**
     * Obtiene solo los libros que están disponibles para préstamo.
     * @return Lista de objetos Libro con estado "Disponible"
     * @throws SQLException si hay error de BD
     */
    public List<Libro> obtenerDisponibles() throws SQLException {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT id, titulo, estado FROM libros WHERE estado = 'Disponible' ORDER BY titulo";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Libro l = new Libro();
                l.setId(rs.getInt("id"));
                l.setTitulo(rs.getString("titulo"));
                l.setEstado(rs.getString("estado"));
                libros.add(l);
            }
        }
        return libros;
    }

    /**
     * Actualiza el estado de un libro (ej. "Disponible", "Prestado").
     * @param id ID del libro
     * @param estado Nuevo estado
     * @throws SQLException si hay error de BD
     */
    public void actualizarEstado(int id, String estado) throws SQLException {
        String sql = "UPDATE libros SET estado = ? WHERE id = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, estado);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }

    /**
     * Actualiza el estado de un libro usando una conexión existente (dentro de una transacción).
     * @param con Conexión activa
     * @param id ID del libro
     * @param estado Nuevo estado
     * @throws SQLException si hay error de BD
     */
    public void actualizarEstado(Connection con, int id, String estado) throws SQLException {
        String sql = "UPDATE libros SET estado = ? WHERE id = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, estado);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }
}