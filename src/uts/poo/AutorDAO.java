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
 * Clase DAO (Data Access Object) para la entidad Autor.
 * Encapsula todas las operaciones de base de datos relacionadas con autores.
 * @author emilio
 */
public class AutorDAO {

    /**
     * Obtiene todos los autores con el conteo de libros que tienen.
     * @return Lista de arreglos Object[] {id, nombre, nacionalidad, totalLibros}
     * @throws SQLException si hay error de BD
     */
    public List<Object[]> obtenerTodosConLibros() throws SQLException {
        List<Object[]> autores = new ArrayList<>();
        String sql = "SELECT a.id, a.nombre, a.nacionalidad, "
                + "(SELECT COUNT(*) FROM libros l WHERE l.autor_id = a.id) AS total_libros "
                + "FROM autores a ORDER BY a.id";

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
}