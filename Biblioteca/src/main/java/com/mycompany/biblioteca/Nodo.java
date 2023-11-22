/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.biblioteca;

import java.io.Serializable;


/**
 * Clase Nodo para representar un elemento en una lista doblemente enlazada.
 * Cada nodo contiene un libro, una referencia al siguiente nodo y una referencia al nodo anterior.
 * 
 * @author C,J,I,S
 */
public class Nodo implements Serializable {
    public Libro libro;
    Nodo siguiente, anterior;
    
    /**
     * Constructor de la clase Nodo.
     * 
     * @param libro    Libro que se almacenará en el nodo.
     * @param ant      Nodo anterior en la lista.
     * @param sig      Nodo siguiente en la lista.
     */
    
    public Nodo ( Libro libro, Nodo ant, Nodo sig){
        this.libro=libro;
        siguiente = sig;
        anterior = ant;
        
    }
    
    /**
     * Obtiene el siguiente nodo en la lista.
     * 
     * @return Referencia al siguiente nodo.
     */
    
    public Nodo getSiguiente() {
        return siguiente;
    }
    
    /**
     * Establece el siguiente nodo en la lista.
     * 
     * @param siguiente Nodo siguiente en la lista.
     */
    
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * Obtiene el nodo anterior en la lista.
     * 
     * @return Referencia al nodo anterior.
     */
    
    public Nodo getAnterior() {
        return anterior;
    }

     /**
     * Establece el nodo anterior en la lista.
     * 
     * @param anterior Nodo anterior en la lista.
     */
    
    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }
}

   
