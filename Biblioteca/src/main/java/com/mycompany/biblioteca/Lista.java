/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.biblioteca;

/**
 *
 * @author ***
 */
public class Lista {
    
    private Libro iNodo ;
    private Libro fNodo;
    
    
    public Lista(){
        iNodo = null;
        fNodo = null;
    }
      
    
    public void agregar (Libro libros){
           if (iNodo == null){
               iNodo = libros;
               fNodo = libros;
           }else {
               libros.setSiguiente(iNodo);
               iNodo.setAnterior(libros);
               iNodo = libros;
           }
        
    }
}
