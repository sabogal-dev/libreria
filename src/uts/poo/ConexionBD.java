/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.poo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase que gestiona la conexión a la base de datos SQLite y la creación de tablas.
 * @author emilio
 */
public class ConexionBD {

    private static final String URL = "jdbc:sqlite:libreria.db";

    /**
     * Obtiene una conexión a la base de datos SQLite.
     * @return Connection activa
     * @throws SQLException si falla la conexión
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    /**
     * Inicializa la base de datos creando las tablas si no existen.
     */
    public static void inicializarBD() {
        String sqlLibros = "CREATE TABLE IF NOT EXISTS libros ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "titulo TEXT NOT NULL,"
                + "genero TEXT,"
                + "calificacion REAL DEFAULT 0,"
                + "estado TEXT DEFAULT 'Disponible',"
                + "autor_id INTEGER"
                + ")";

        String sqlAutores = "CREATE TABLE IF NOT EXISTS autores ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nombre TEXT NOT NULL,"
                + "nacionalidad TEXT"
                + ")";

        String sqlUsuarios = "CREATE TABLE IF NOT EXISTS usuarios ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nombre TEXT NOT NULL,"
                + "nacionalidad TEXT,"
                + "email TEXT,"
                + "telefono TEXT"
                + ")";

        String sqlPrestamos = "CREATE TABLE IF NOT EXISTS prestamos ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "libro_id INTEGER NOT NULL,"
                + "usuario_id INTEGER NOT NULL,"
                + "fecha_prestamo TEXT NOT NULL,"
                + "fecha_devolucion TEXT,"
                + "estado TEXT DEFAULT 'Activo',"
                + "FOREIGN KEY (libro_id) REFERENCES libros(id),"
                + "FOREIGN KEY (usuario_id) REFERENCES usuarios(id)"
                + ")";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlLibros);
            stmt.execute(sqlAutores);
            stmt.execute(sqlUsuarios);
            stmt.execute(sqlPrestamos);

            // Verificar si hay datos para insertar datos de ejemplo
            var rs = stmt.executeQuery("SELECT COUNT(*) FROM autores");
            if (rs.next() && rs.getInt(1) == 0) {
                insertarDatosEjemplo(stmt);
            }
            rs.close();

            System.out.println("Base de datos inicializada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }

    private static void insertarDatosEjemplo(Statement stmt) throws SQLException {
        // Autores
        stmt.execute("INSERT INTO autores (nombre, nacionalidad) VALUES "
                + "('James Clear', 'Estadounidense'),"
                + "('Cal Newport', 'Estadounidense'),"
                + "('Dot Hutchison', 'Estadounidense'),"
                + "('Homero', 'Griego')");

        // Usuarios
        stmt.execute("INSERT INTO usuarios (nombre, nacionalidad, email, telefono) VALUES "
                + "('David Sabogal', 'Colombiano', 'desabogal@uts.edu.co', '3195477153')");

        // Libros
        stmt.execute("INSERT INTO libros (titulo, genero, calificacion, estado, autor_id) VALUES "
                + "('Hábitos Atómicos', 'Desarrollo Personal', 4.7, 'Disponible', 1),"
                + "('Deep Work', 'Productividad', 4.5, 'Disponible', 2),"
                + "('El Jardín de las Mariposas', 'Thriller', 4.3, 'Disponible', 3),"
                + "('La Odisea', 'Épica', 4.9, 'Disponible', 4),"
                + "('Las Rosas de Mayo', 'Thriller', 4.4, 'Disponible', 3)");

        System.out.println("Datos de ejemplo insertados correctamente.");
    }
}