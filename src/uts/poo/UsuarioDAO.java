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
 * Clase DAO (Data Access Object) para la entidad Usuario.
 * Encapsula todas las operaciones de base de datos relacionadas con usuarios.
 * @author emilio
 */
public class UsuarioDAO {

    /**
     * Obtiene todos los usuarios registrados.
     * @return Lista de objetos Usuario
     * @throws SQLException si hay error de BD
     */
    public List<Usuario> obtenerTodos() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id, nombre FROM usuarios ORDER BY nombre";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                usuarios.add(u);
            }
        }
        return usuarios;
    }

    /**
     * Autentica un usuario por nombre y clave (texto plano).
     * @param nombre Nombre del usuario
     * @param clave  Clave en texto plano
     * @return Usuario autenticado, o null si falla
     * @throws SQLException si hay error de BD
     */
    public Usuario autenticar(String nombre, String clave) throws SQLException {
        String sql = "SELECT id, nombre, nacionalidad, email, telefono, clave FROM usuarios WHERE nombre = ? AND clave = ?";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, nombre);
            pst.setString(2, clave);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("nacionalidad"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("clave")
                    );
                }
            }
        }
        return null;
    }
}
