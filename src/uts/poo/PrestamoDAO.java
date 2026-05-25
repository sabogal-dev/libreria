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
 * Clase DAO (Data Access Object) para la entidad Prestamo.
 * Encapsula todas las operaciones de base de datos relacionadas con préstamos.
 * @author emilio
 */
public class PrestamoDAO {

    /**
     * Obtiene todos los préstamos con información del libro y usuario.
     * @return Lista de arreglos Object[] {id, libroTitulo, usuarioNombre, fechaPrestamo, fechaDevolucion, estado}
     * @throws SQLException si hay error de BD
     */
    public List<Object[]> obtenerTodos() throws SQLException {
        List<Object[]> prestamos = new ArrayList<>();
        String sql = "SELECT p.id, l.titulo AS libro, "
                + "u.nombre AS usuario, "
                + "p.fecha_prestamo, p.fecha_devolucion, p.estado "
                + "FROM prestamos p "
                + "JOIN libros l ON p.libro_id = l.id "
                + "JOIN usuarios u ON p.usuario_id = u.id "
                + "ORDER BY p.id DESC";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                prestamos.add(new Object[]{
                    rs.getInt("id"),
                    rs.getString("libro"),
                    rs.getString("usuario"),
                    rs.getString("fecha_prestamo"),
                    rs.getString("fecha_devolucion"),
                    rs.getString("estado")
                });
            }
        }
        return prestamos;
    }

    /**
     * Cuenta los préstamos activos.
     * @return Número de préstamos activos
     * @throws SQLException si hay error de BD
     */
    public int contarActivos() throws SQLException {
        String sql = "SELECT COUNT(*) FROM prestamos WHERE estado = 'Activo'";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    /**
     * Crea un nuevo préstamo y actualiza el estado del libro a "Prestado".
     * Usa una transacción para garantizar la consistencia.
     * @param libroId ID del libro a prestar
     * @param usuarioId ID del usuario que toma el préstamo
     * @param fechaPrestamo Fecha del préstamo (YYYY-MM-DD)
     * @param fechaDevolucion Fecha estimada de devolución (YYYY-MM-DD)
     * @throws SQLException si hay error de BD
     */
    /**
     * Obtiene el libro_id asociado a un préstamo.
     * @param prestamoId ID del préstamo
     * @return ID del libro
     * @throws SQLException si hay error de BD
     */
    public int obtenerLibroId(int prestamoId) throws SQLException {
        String sql = "SELECT libro_id FROM prestamos WHERE id = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, prestamoId);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next() ? rs.getInt("libro_id") : -1;
            }
        }
    }

    /**
     * Devuelve un libro: cambia estado del préstamo a "Devuelto" y el libro a "Disponible".
     * @param prestamoId ID del préstamo a devolver
     * @throws SQLException si hay error de BD
     */
    public void devolver(int prestamoId) throws SQLException {
        int libroId = obtenerLibroId(prestamoId);
        if (libroId < 0) return;

        String sqlPrestamo = "UPDATE prestamos SET estado = 'Devuelto' WHERE id = ?";
        String sqlLibro = "UPDATE libros SET estado = 'Disponible' WHERE id = ?";

        Connection con = null;
        try {
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);

            try (PreparedStatement pstP = con.prepareStatement(sqlPrestamo)) {
                pstP.setInt(1, prestamoId);
                pstP.executeUpdate();
            }
            try (PreparedStatement pstL = con.prepareStatement(sqlLibro)) {
                pstL.setInt(1, libroId);
                pstL.executeUpdate();
            }

            con.commit();
        } catch (SQLException e) {
            if (con != null) {
                try { con.rollback(); } catch (SQLException ex) {}
            }
            throw e;
        } finally {
            if (con != null) {
                try { con.setAutoCommit(true); con.close(); } catch (SQLException e) {}
            }
        }
    }

    /**
     * Cancela un préstamo: cambia estado a "Cancelado" y el libro a "Disponible".
     * @param prestamoId ID del préstamo a cancelar
     * @throws SQLException si hay error de BD
     */
    public void cancelar(int prestamoId) throws SQLException {
        int libroId = obtenerLibroId(prestamoId);
        if (libroId < 0) return;

        String sqlPrestamo = "UPDATE prestamos SET estado = 'Cancelado' WHERE id = ?";
        String sqlLibro = "UPDATE libros SET estado = 'Disponible' WHERE id = ?";

        Connection con = null;
        try {
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);

            try (PreparedStatement pstP = con.prepareStatement(sqlPrestamo)) {
                pstP.setInt(1, prestamoId);
                pstP.executeUpdate();
            }
            try (PreparedStatement pstL = con.prepareStatement(sqlLibro)) {
                pstL.setInt(1, libroId);
                pstL.executeUpdate();
            }

            con.commit();
        } catch (SQLException e) {
            if (con != null) {
                try { con.rollback(); } catch (SQLException ex) {}
            }
            throw e;
        } finally {
            if (con != null) {
                try { con.setAutoCommit(true); con.close(); } catch (SQLException e) {}
            }
        }
    }

    /**
     * Obtiene todos los préstamos de un usuario específico con información del libro.
     * @param usuarioId ID del usuario autenticado
     * @return Lista de arreglos Object[] {id, libroTitulo, fechaPrestamo, fechaDevolucion, estado, atrasado}
     * @throws SQLException si hay error de BD
     */
    public List<Object[]> obtenerPorUsuario(int usuarioId) throws SQLException {
        List<Object[]> prestamos = new ArrayList<>();
        String sql = "SELECT p.id, l.titulo AS libro, p.fecha_prestamo, p.fecha_devolucion, p.estado "
                + "FROM prestamos p "
                + "JOIN libros l ON p.libro_id = l.id "
                + "WHERE p.usuario_id = ? "
                + "ORDER BY p.id DESC";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, usuarioId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String estado = rs.getString("estado");
                    String fechaDev = rs.getString("fecha_devolucion");
                    // Determinar si está atrasado: Activo y fecha_devolucion < hoy
                    boolean atrasado = "Activo".equals(estado) && fechaDev != null
                            && fechaDev.compareTo(java.time.LocalDate.now().toString()) < 0;
                    prestamos.add(new Object[]{
                        rs.getInt("id"),
                        rs.getString("libro"),
                        rs.getString("fecha_prestamo"),
                        fechaDev,
                        estado,
                        atrasado ? "Sí" : "No"
                    });
                }
            }
        }
        return prestamos;
    }

    public void crearPrestamo(int libroId, int usuarioId, String fechaPrestamo, String fechaDevolucion) throws SQLException {
        String sqlPrestamo = "INSERT INTO prestamos (libro_id, usuario_id, fecha_prestamo, fecha_devolucion, estado) "
                + "VALUES (?, ?, ?, ?, 'Activo')";
        String sqlActualizarLibro = "UPDATE libros SET estado = 'Prestado' WHERE id = ?";

        Connection con = null;
        try {
            con = ConexionBD.getConnection();
            con.setAutoCommit(false); // Iniciar transacción

            // Insertar préstamo
            try (PreparedStatement pstPrestamo = con.prepareStatement(sqlPrestamo)) {
                pstPrestamo.setInt(1, libroId);
                pstPrestamo.setInt(2, usuarioId);
                pstPrestamo.setString(3, fechaPrestamo);
                pstPrestamo.setString(4, fechaDevolucion);
                pstPrestamo.executeUpdate();
            }

            // Actualizar estado del libro a "Prestado"
            try (PreparedStatement pstLibro = con.prepareStatement(sqlActualizarLibro)) {
                pstLibro.setInt(1, libroId);
                pstLibro.executeUpdate();
            }

            con.commit(); // Confirmar transacción
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // Revertir en caso de error
                } catch (SQLException ex) {
                    System.err.println("Error en rollback: " + ex.getMessage());
                }
            }
            throw e; // Relanzar para que la vista lo maneje
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar conexión: " + e.getMessage());
                }
            }
        }
    }
}