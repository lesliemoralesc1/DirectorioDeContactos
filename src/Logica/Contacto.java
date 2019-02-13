/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.Serializable;

/**
 *
 * @author LESLIE MORALES
 */
public class Contacto implements Serializable {

    public String nombre;
    public int telefono;
    public String direccionpostal;
    public String email;
    private Contacto padre,izquierdo, derecho;

    // constructor por defecto
    public Contacto() {
        nombre = null;
        telefono = 0;
        direccionpostal = null;
        email = null;
        padre = null;
        izquierdo = null;
        derecho = null;
    }

    /**
     *
     * @param nombre
     */
    public Contacto(String nombre) {
        this.nombre = nombre;
    }
// Constructor alternativo

    /**
     *
     * @param nombre
     * @param telefono
     * @param direccionpostal
     * @param email
     */
    public Contacto(String nombre, int telefono, String direccionpostal, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccionpostal = direccionpostal;
        this.email = email;
        this.padre=null;
        this.izquierdo=null;
        this.derecho= null;
    }
/**
 * 
 * @param nombre
 * @param telefono
 * @param direccionpostal
 * @param email
 * @param padre
 * @param izquierdo
 * @param derecho 
 */
    public Contacto(String nombre, int telefono, String direccionpostal, String email,Contacto padre, Contacto izquierdo, Contacto derecho) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccionpostal = direccionpostal;
        this.email = email;
        this.padre = padre;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

//constructor copia
    /**
     *
     * @param c
     */
    public Contacto(Contacto c) {
        nombre = c.getNombre();
        telefono = c.getTelefono();
        direccionpostal = c.getDireccionpostal();
        email = c.getEmail();
        padre = c.getPadre();
        izquierdo = c.getIzquierdo();
        derecho = c.getDerecho();
    }

    public Contacto getPadre() {
        return padre;
    }

    public void setPadre(Contacto padre) {
        this.padre = padre;
    }

    public Contacto getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Contacto izquierdo) {
        this.izquierdo = izquierdo;
    }

    public Contacto getDerecho() {
        return derecho;
    }

    public void setDerecho(Contacto derecho) {
        this.derecho = derecho;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccionpostal() {
        return direccionpostal;
    }

    public void setDireccionpostal(String direccionpostal) {
        this.direccionpostal = direccionpostal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return nombre + " su telefono es " + telefono + " y su direccion es " + direccionpostal + " y su correo es " + email;
    }

}
