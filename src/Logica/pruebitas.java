/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Vista.Directorio;

/**
 *
 * @author Leslie M
 */
public class pruebitas {
    public static void main(String[] args) {
        Arbol ar = new Arbol();
        Directorio ventana = new Directorio();
        ventana.setVisible(true);
        Contacto b = new Contacto();
       String Nombre = "jose";
       int telefono =85045;
       String direccion = " por ahi";
       String email = " lola@hor.com";
       b.setNombre(Nombre);
       b.setTelefono(telefono);
       b.setDireccionpostal(direccion);
       b.setEmail(email);
       ar.insertar(b);
      ar.alturaArbol(ar.getRaiz());
    }
}
