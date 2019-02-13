/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivo;
import Logica.Arbol;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
// Esta clase contiene los metodos para cargar y guardar los datos del programa que son guardados con un archivo binario
// Los metodos son staticos porque no es necesario instanciar la clase para usar los metodos
//Utilice el siguiente tutorial para realizarla http://www.jc-mouse.net/java/archivos-binarios-en-java-lecturaescritura
/**
 *
 * @author Leslie Morales Codigo 0221710026
 */
public class ArchivoDirectorio {
      
    public static boolean guardarInfo(Arbol objeto) {

        try {
            ObjectOutputStream guardar = new ObjectOutputStream(new FileOutputStream("ArchivoDirectorio.bin"));
            guardar.writeObject(objeto);
            return true;

        } catch (IOException e) {
            return false;
        }
    }

    public static Arbol cargar() {

        ObjectInputStream cargar;
        try {
            cargar = new ObjectInputStream(new FileInputStream("ArchivoDirectorio.bin"));
            Arbol info = (Arbol) cargar.readObject();
            return info;
        } catch (FileNotFoundException ex) {
            return new Arbol();
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }

    }

}
