package uts.poo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO (Data Access Object) para la entidad Libro.
 * Encapsula todas las operaciones CRUD y consultas relacionadas con libros.
 * Soporta soft-delete mediante columna "activo".
 * @author emilio
 */
public class LibroDAO {

    /**
     * Obtiene todos los libros activos de la base de datos.
     * @return Lista de objetos Libro
     * @throws SQLException si hay error de BD
     */
    public List<Libro> obtenerTodos() throws SQLException {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT id, titulo, genero, calificacion, estado, autor_id "
                + "FROM libros WHERE activo = 1 ORDER BY id";

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
                l.setAutorId(rs.getInt("autor_id"));
                libros.add(l);
            }
        }
        return libros;
    }

    /**
     * Obtiene solo los libros activos que están disponibles para préstamo.
     * @return Lista de objetos Libro con estado "Disponible"
     * @throws SQLException si hay error de BD
     */
    public List<Libro> obtenerDisponibles() throws SQLException {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT id, titulo, estado FROM libros WHERE activo = 1 AND estado = 'Disponible' ORDER BY titulo";

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
     * Crea un nuevo libro en la base de datos.
     * @param libro Objeto Libro con título, género, calificación y autorId
     * @throws SQLException si hay error de BD
     */
    public void crear(Libro libro) throws SQLException {
        String sql = "INSERT INTO libros (titulo, genero, calificacion, autor_id) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, libro.getTitulo());
            pst.setString(2, libro.getGenero());
            pst.setDouble(3, libro.getCalificacion());
            pst.setInt(4, libro.getAutorId());
            pst.executeUpdate();
        }
    }

    /**
     * Actualiza los datos de un libro existente.
     * @param libro Objeto Libro con id, título, género, calificación y autorId
     * @throws SQLException si hay error de BD
     */
    public void actualizar(Libro libro) throws SQLException {
        String sql = "UPDATE libros SET titulo = ?, genero = ?, calificacion = ?, autor_id = ? WHERE id = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, libro.getTitulo());
            pst.setString(2, libro.getGenero());
            pst.setDouble(3, libro.getCalificacion());
            pst.setInt(4, libro.getAutorId());
            pst.setInt(5, libro.getId());
            pst.executeUpdate();
        }
    }

    /**
     * Archiva un libro (soft-delete: activo = 0).
     * @param id ID del libro a archivar
     * @throws SQLException si hay error de BD
     */
    public void archivar(int id) throws SQLException {
        String sql = "UPDATE libros SET activo = 0 WHERE id = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
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