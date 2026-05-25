/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.poo;

/**
 * Clase que representa un Autor de libros.
 * @author emilio
 */
public class Autor extends Persona {
    private String fechaNacimiento;
    private int librosEscritos;

    public Autor() {
    }

    public Autor(int id, String nombre, String nacionalidad, String fechaNacimiento, int librosEscritos) {
        super(id, nombre, nacionalidad);
        this.fechaNacimiento = fechaNacimiento;
        this.librosEscritos = librosEscritos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getLibrosEscritos() {
        return librosEscritos;
    }

    public void setLibrosEscritos(int librosEscritos) {
        this.librosEscritos = librosEscritos;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", nacionalidad='" + getNacionalidad() + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", librosEscritos=" + librosEscritos +
                '}';
    }
}