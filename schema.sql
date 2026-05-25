-- ===================================================
-- Script de creación de tablas para la BD Librería
-- Base de datos: SQLite
-- Ejecutar con: sqlite3 libreria.db < schema.sql
-- ===================================================

-- Tabla de autores
CREATE TABLE IF NOT EXISTS autores (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    nacionalidad TEXT,
    activo INTEGER DEFAULT 1
);

-- Tabla de libros
CREATE TABLE IF NOT EXISTS libros (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    titulo TEXT NOT NULL,
    genero TEXT,
    calificacion REAL DEFAULT 0,
    estado TEXT DEFAULT 'Disponible',
    autor_id INTEGER,
    activo INTEGER DEFAULT 1,
    FOREIGN KEY (autor_id) REFERENCES autores(id)
);

-- Tabla de usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    nacionalidad TEXT,
    email TEXT,
    telefono TEXT,
    clave TEXT NOT NULL DEFAULT '1234'
);

-- Tabla de préstamos
CREATE TABLE IF NOT EXISTS prestamos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    libro_id INTEGER NOT NULL,
    usuario_id INTEGER NOT NULL,
    fecha_prestamo TEXT NOT NULL,
    fecha_devolucion TEXT,
    estado TEXT DEFAULT 'Activo',
    FOREIGN KEY (libro_id) REFERENCES libros(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Datos de ejemplo (opcional, ejecutar solo si se desea)

-- INSERT INTO autores (nombre, nacionalidad) VALUES
--     ('James Clear', 'Estadounidense'),
--     ('Cal Newport', 'Estadounidense'),
--     ('Dot Hutchison', 'Estadounidense'),
--     ('Homero', 'Griego');

-- INSERT INTO usuarios (nombre, nacionalidad, email, telefono, clave) VALUES
--     ('David Sabogal', 'Colombiano', 'desabogal@uts.edu.co', '3195477153', '1234'),
--     ('Admin', 'Colombiano', 'admin@uts.edu.co', '3000000000', 'admin'),
--     ('Juan Perez', 'Colombiano', 'juan@uts.edu.co', '3112223344', '5678');

-- INSERT INTO libros (titulo, genero, calificacion, estado, autor_id) VALUES
--     ('Hábitos Atómicos', 'Desarrollo Personal', 4.7, 'Disponible', 1),
--     ('Deep Work', 'Productividad', 4.5, 'Disponible', 2),
--     ('El Jardín de las Mariposas', 'Thriller', 4.3, 'Disponible', 3),
--     ('La Odisea', 'Épica', 4.9, 'Disponible', 4),
--     ('Las Rosas de Mayo', 'Thriller', 4.4, 'Disponible', 3);