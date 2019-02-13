/**
 * Palabra de honor:
 * - No he discutido ni mostrado el código de mi programa con alguien que no sea mi *compañero, Profesor o con el monitor asignado a este curso.
 *
 * - No he utilizado código obtenido de otro u otros estudiantes,
 * O cualquier otra fuente no autorizada, ya sea modificado o sin modificar.
 *
 * - Si cualquier código o documentación utilizada en mi programa
 * Fue obtenido de otra fuente, tal como un libro de texto o curso
 * Notas, debe ser claramente señalado con una cita apropiada en
 * Los comentarios de mi programa.
 *
 * <Leslie Liseth Morales Casseres – 0221710026>
 * <Jose Carlos Garcia Velez- 0221710011>
 ************************************************** ********************* /
 * To change this license header, choose License Headers in Project Properties.
 * https://techlandia.com/comparar-cadenas-ordenadas-alfabeticamente-java-como_263665/
 * https://www.youtube.com/watch?v=6taCAJ9ozHc&list=PLaxZkGlLWHGXHAZQoL17MC3lAxZfGv-Da&index=4
 */
package Logica;

import Archivo.ArchivoDirectorio;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Leslie Liseth MORALES CASSERES #0221710026 y Jose Carlos Garcia Velez
 * #0221710011
 */
public class Arbol implements Serializable {

    private Contacto raiz;
    ArrayList contactosordenados = new ArrayList();

    public Arbol(Contacto raiz) {
        this.raiz = raiz;
    }

    public Arbol() {
        this.raiz = null;
    }

    public Contacto getRaiz() {
        return raiz;
    }

    public void setRaiz(Contacto raiz) {
        this.raiz = raiz;
    }

    public boolean verificarRaiz(Contacto con) {
        return raiz == con;
    }

    public int comparacion(String primero, String segundo) {
        int comparar;
        return comparar = primero.compareToIgnoreCase(segundo);
    }

    public boolean eshoja(Contacto con) {
        return con.getIzquierdo() == null && con.getDerecho() == null;
    }

    public boolean esinterno(Contacto con) {
        return !eshoja(con);
    }

    public boolean estaVacia() {
        return raiz == null;
    }

    public int cantidadcontactos() {
        return contactosordenados.size();
    }

    public boolean hijoIzquierdo(Contacto con) {
        return con.getIzquierdo() != null && con.getDerecho() == null;
    }

    public void ingresoArchivo() {
        ArchivoDirectorio.guardarInfo(this);
    }

    public static Arbol mostrarDatosArchivo() {
        Arbol cargar = ArchivoDirectorio.cargar();
        return ArchivoDirectorio.cargar();
    }

    /**
     * Se reciben todos los datos del contacto para luego ingresarlos al arbol
     *
     * @param contac
     */
    public void insertar(Contacto a) {
        insertar(a, this.raiz);
    }

    public void insertar(Contacto contac, Contacto aux) {
        Contacto nuevo = new Contacto(contac.getNombre(), contac.getTelefono(), contac.getDireccionpostal(), contac.getEmail(), null, null, null);
        System.out.println("paso por aqui");
        if (estaVacia()) {//verificamos si esta vacio
            this.raiz = nuevo;
            System.out.println("es raiz");
        } else {
            if (comparacion(aux.getNombre(), nuevo.getNombre()) != 0) {// verificamos que los nombres sean diferentes
                do {
                    if (comparacion(aux.getNombre(), nuevo.getNombre()) > 0) {//Si el auxiliar es mayor que el nuevo
                        if (aux.getIzquierdo() == null) {
                            nuevo.setPadre(aux);
                            aux.setIzquierdo(nuevo);
                            System.out.println("inserto 1");
                            break;
                        } else {
                            insertar(contac, aux.getIzquierdo());
                        }
                    }
                    if (comparacion(aux.getNombre(), nuevo.getNombre()) < 0) {//Si el auxiliar es menor que el nuevo
                        if (aux.getDerecho() == null) {
                            nuevo.setPadre(aux);
                            aux.setDerecho(nuevo);
                            System.out.println("inserto 2");
                            break;
                        } else {
                            insertar(contac, aux.getDerecho());
                        }
                    }
                    if (comparacion(aux.getNombre(), nuevo.getNombre()) == 0) {//Si el auxiliar y el nuevo son iguales
                        break;
                    }
                } while (null != aux);
            } else {
                System.out.println("ESTE NOMBRE YA EXISTE");
            }//end else
        }//end else
        System.out.println("paso");
        ingresoArchivo();//SE GUARDA Y ACTUALIZA LA INFORMACION DEL ARCHIVO
    }//end metodo insertar

    public boolean eliminar(Contacto nombre) {
        
        if (nombre == null) {//verificamos que el contacto esta vacio
            System.out.println("EL contacto esta vacio");
            return false;
        } else {//Si no esta vacio
            /* Creamos variables para saber si tiene hijos izquierdo y derecho */
            boolean tieneNodoDerecha = nombre.getDerecho() != null ? true : false;
            boolean tieneNodoIzquierda = nombre.getIzquierdo() != null ? true : false;

            /* Verificamos los 3 casos diferentes y llamamos a la función correspondiente */
 /* Caso 1: No tiene hijos */
            if (!tieneNodoDerecha && !tieneNodoIzquierda) {
                return eliminarNodoCaso1(nombre);
            }

            /* Caso 2: Tiene un hijo y el otro no */
            if (tieneNodoDerecha && !tieneNodoIzquierda) {
                return eliminarCaso2(nombre);
            }

            /* Caso 2: Tiene un hijo y el otro no */
            if (!tieneNodoDerecha && tieneNodoIzquierda) {
                return eliminarCaso2(nombre);
            }

            /* Caso 3: Tiene ambos hijos */
            if (tieneNodoDerecha && tieneNodoIzquierda) {
                return eliminarCaso3(nombre);
            }
        }

        ingresoArchivo();//SE GUARDA Y ACTUALIZA LA INFORMACION DEL ARCHIVO
        return false;
    }// fin metodo eliminar

    private boolean eliminarNodoCaso1(Contacto c) {
        /* lo único que hay que hacer es borrar el nodo y establecer el apuntador de su padre a nulo */

 /*
     * Guardemos los hijos del padre temporalmente para saber cuál de sus hijos hay que 
     * eliminar
         */
        Contacto hijoIzquierdo = c.getPadre().getIzquierdo();
        Contacto hijoDerecho = c.getPadre().getDerecho();

        if (hijoIzquierdo == c) {
            c.getPadre().setIzquierdo(null);
            return true;
        }

        if (hijoDerecho == c) {
            c.getPadre().setDerecho(null);
            return true;
        }

        return false;
    }

    private boolean eliminarCaso2(Contacto c) {
        /* Borrar el Nodo y el subárbol que tenía pasa a ocupar su lugar */

 /*
         * Guardemos los hijos del padre temporalmente para saber cuál de sus hijos hay que 
         * eliminar
         */
        Contacto hijoIzquierdo = c.getPadre().getIzquierdo();
        Contacto hijoDerecho = c.getPadre().getDerecho();

        /*
         * Buscamos el hijo existente del nodo que queremos eliminar
         */
        Contacto hijoActual = c.getIzquierdo() != null
                ? c.getIzquierdo() : c.getDerecho();//

        if (hijoIzquierdo == c) {
            c.getPadre().setIzquierdo(hijoActual);

            /* Eliminando todas las referencias hacia el nodo */
            hijoActual.setPadre(c.getPadre());
            c.setDerecho(null);
            c.setIzquierdo(null);

            return true;
        }

        if (hijoDerecho == c) {
            c.getPadre().setDerecho(hijoActual);

            /* Eliminando todas las referencias hacia el nodo */
            hijoActual.setPadre(c.getPadre());
            c.setDerecho(null);
            c.setIzquierdo(null);

            return true;
        }

        return false;
    }

    private boolean eliminarCaso3(Contacto c) {
        /* Tomar el hijo derecho del Nodo que queremos eliminar */
        Contacto nodoMasALaIzquierda = buscarHijoMenor(c.getDerecho());
        if (nodoMasALaIzquierda != null) {
            /*
         * Reemplazamos el valor del nodo que queremos eliminar por el nodo que encontramos 
             */
            c.setNombre(nodoMasALaIzquierda.getNombre());
            /* 
         * Eliminar este nodo de las formas que conocemos ( caso 1, caso 2 ) 
             */
            eliminar(nodoMasALaIzquierda);
            return true;
        }
        return false;
    }

    /**
     * Recorrer de forma recursiva hasta encontrar el nodo más a la izquierda
     *
     * @param hijo
     * @return
     */
    public Contacto buscarHijoMenor(Contacto hijo) {

        if (hijo.getIzquierdo() != null) {
            return buscarHijoMenor(hijo.getIzquierdo());
        }
        return hijo;
    }

    /**
     *
     * @param nombre
     * @return Contacto
     */
    public Contacto buscar(String nombre) {
        Contacto aux = raiz;
        while (comparacion(aux.nombre, nombre) != 0) {//Mientras el aux sea diferente al nombre que buscamos

            if (comparacion(aux.nombre, nombre) > 0) {// Si el nombre es menor a aux
                aux = aux.getIzquierdo();// apuntamos al aux del hijo izquierdo
            } else if (comparacion(aux.nombre, nombre) < 0) {
                aux = aux.getDerecho();// apuntamos al hijo derecho
            }
            if (aux == null) {
                return null;//no encontró el nombre
            }
        }
        return aux;
    }

    /**
     *
     * @param reco
     * @return ArrayList
     */
    private ArrayList<Contacto> ordenar(Contacto reco) {

        if (reco != null) {
            contactosordenados.add(Arbol.this.ordenar(reco.getIzquierdo()));
            contactosordenados.add(reco);
            System.out.print(reco.getNombre() + " ");
            contactosordenados.add(Arbol.this.ordenar(reco.getDerecho()));
        }
        return contactosordenados;
    }

    /**
     *
     * @return ArrayList
     */
    public ArrayList ordenarA() {//MANDAMOS LA RAIZ COMO PARAMETRO Y RETORNAMOS LA LISTA
        return Arbol.this.ordenar(raiz);
    }

    /**
     *
     * @param arbol
     * @param cont
     */
    public void mostrarArbol(Contacto arbol, int cont) {
        if (arbol != null) {
            mostrarArbol(arbol.getDerecho(), cont + 1);
            for (int i = 0; i < cont; i++) {
                System.out.print("     ");
            }
            System.out.println(arbol.getNombre());
            mostrarArbol(arbol.getIzquierdo(), cont + 1);
        }
    }

    /**
     *
     * @param con
     * @return int
     */
    public int alturaArbol(Contacto con) {
        int altura = 0;
        if (esinterno(con)) {
            if (con.getIzquierdo() != null) {
                altura = Math.max(altura, alturaArbol(con.getIzquierdo()));//Me dice cual de los tiene mas altura por la rama izquierda
            }
            if (con.getDerecho() != null) {
                altura = Math.max(altura, alturaArbol(con.getDerecho()));// Me dice cual de los dos tiene mas altura por la rama derecha
            }
            altura++;
        }
        return altura;
    }

    /**
     *
     * @param con
     * @return int
     */
    public int profundidadArbol(Contacto con) {
        int profundidad = 0;
        if (con != getRaiz()) {
            profundidad = 1 + profundidadArbol(con.getPadre());
        }
        return profundidad;
    }

    /**
     *
     * @return int
     */
    public int cantidadhoja() {
        Contacto aux = raiz;
        int hojas = 0;
        if (eshoja(aux)) {
            if (eshoja(aux.getIzquierdo())) {
                hojas++;
            } else {
                aux = aux.getIzquierdo();
            }
            if (eshoja(aux.getDerecho())) {
                hojas++;
            } else {
                aux = aux.getDerecho();
            }
        }
        return hojas;
    }

    /**
     *
     * @return ArrayList<Contacto>
     */
    public ArrayList<Contacto> nombreshoja() {
        Contacto aux = raiz;
        ArrayList hojas = new ArrayList();
        if (!eshoja(aux)) {
            if (eshoja(aux.getIzquierdo())) {
                hojas.add(aux.getIzquierdo().getNombre());
            } else {
                aux = aux.getIzquierdo();
            }
            if (eshoja(aux.getDerecho())) {
                hojas.add(aux.getDerecho().getNombre());
            } else {
                aux = aux.getDerecho();
            }
        } else {
            System.out.println("Solo esta la raiz");
        }
        return hojas;
    }

    @Override
    public String toString() {
        return "Arbol{" + " La raiz es =" + raiz + '}';
    }

}
