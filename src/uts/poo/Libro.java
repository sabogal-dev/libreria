/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.poo;

/**
 * Clase que representa un Libro en la biblioteca.
 * @author emilio
 */
public class Libro {
    private int id;
    private String titulo;
    private String genero;
    private double calificacion;
    private String estado; // "Disponible" o "Prestado"
    private int autorId;

    public Libro() {
    }

    public Libro(int id, String titulo, String genero, double calificacion, String estado, int autorId) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.calificacion = calificacion;
        this.estado = estado;
        this.autorId = autorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
    }

    @Override
    public String toString() {
        return id + " - " + titulo;
    }
}