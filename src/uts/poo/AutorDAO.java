package uts.poo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para la entidad Autor.
 * Soporta CRUD completo y soft-delete.
 * @author emilio
 */
public class AutorDAO {

    /**
     * Obtiene todos los autores activos con conteo de libros.
     */
    public List<Object[]> obtenerTodosConLibros() throws SQLException {
        List<Object[]> autores = new ArrayList<>();
        String sql = "SELECT a.id, a.nombre, a.nacionalidad, "
                + "(SELECT COUNT(*) FROM libros l WHERE l.autor_id = a.id AND l.activo = 1) AS total_libros "
                + "FROM autores a WHERE a.activo = 1 ORDER BY a.id";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                autores.add(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("nacionalidad"),
                    rs.getInt("total_libros")
                });
            }
        }
        return autores;
    }

    /**
     * Obtiene todos los autores activos (solo id y nombre) para combos.
     */
    public List<Autor> obtenerTodos() throws SQLException {
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT id, nombre, nacionalidad FROM autores WHERE activo = 1 ORDER BY nombre";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Autor a = new Autor();
                a.setId(rs.getInt("id"));
                a.setNombre(rs.getString("nombre"));
                a.setNacionalidad(rs.getString("nacionalidad"));
                autores.add(a);
            }
        }
        return autores;
    }

    /**
     * Crea un nuevo autor.
     */
    public void crear(Autor autor) throws SQLException {
        String sql = "INSERT INTO autores (nombre, nacionalidad) VALUES (?, ?)";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, autor.getNombre());
            pst.setString(2, autor.getNacionalidad());
            pst.executeUpdate();
        }
    }

    /**
     * Actualiza un autor existente.
     */
    public void actualizar(Autor autor) throws SQLException {
        String sql = "UPDATE autores SET nombre = ?, nacionalidad = ? WHERE id = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, autor.getNombre());
            pst.setString(2, autor.getNacionalidad());
            pst.setInt(3, autor.getId());
            pst.executeUpdate();
        }
    }

    /**
     * Archiva un autor (soft-delete: activo = 0).
     */
    public void archivar(int id) throws SQLException {
        String sql = "UPDATE autores SET activo = 0 WHERE id = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }
}