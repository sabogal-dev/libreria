# Proyecto Librería

## Programación Orientada a Objetos
**Universidad Tecnológica de Santander (UTS)**  
**Autor:** David Emilio Sabogal Herreño  
**Corte 3 – 2026**

---

## Descripción

Aplicación Java de escritorio (Swing) para la gestión de una librería. Permite administrar **libros**, **autores** y **préstamos** conectándose a una base de datos SQLite local.

## Funcionalidades

- **Panel de inicio** con estadísticas (total de libros, total prestados, media de calificación)
- **Listado de libros** con ID, título, género, calificación y estado
- **Listado de autores** con conteo de libros por autor
- **Módulo de préstamos**:
  - Ver todos los préstamos con detalle de libro y usuario
  - Registrar nuevos préstamos seleccionando libro y usuario
  - Cambio automático de estado del libro a "Prestado"
- **Gestión de usuarios** registrados en el sistema


## Tecnologías

- **Java 8+** (Swing para la GUI)
- **SQLite** (base de datos local, archivo `.db`)


## Ejecución

1. Abrir el proyecto en NetBeans
2. Limpiar y construir (_Clean and Build_)
3. Ejecutar la clase principal: `uts.poo.vista.MenuPrincipal`

La base de datos se crea automáticamente en la raíz del proyecto con tablas y datos de ejemplo al iniciar la aplicación por primera vez.