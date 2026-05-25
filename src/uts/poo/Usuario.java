/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.poo;

/**
 * Clase que representa un Usuario que puede tomar libros prestados.
 * @author emilio
 */
public class Usuario extends Persona {
    private String email;
    private String telefono;
    private String clave;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String nacionalidad, String email, String telefono, String clave) {
        super(id, nombre, nacionalidad);
        this.email = email;
        this.telefono = telefono;
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public String toString() {
        return getId() + " - " + getNombre();
    }
}