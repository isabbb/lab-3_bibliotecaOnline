    /*
    * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
    * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
    */
    package com.mycompany.biblioteca;

import java.io.Serializable;


    /**
    *
    * @author ***
    */
    public class Lista implements Serializable{

    private Nodo iNodo, fNodo;


    public Lista (){
        iNodo = null;
        fNodo = null;
    }

 
    public boolean verificar (){
       return iNodo !=null;
    }

    public void agregar(Libro libro) {
    Nodo nuevoNodo = new Nodo(libro);
    
    if (iNodo == null) {
        // If the list is empty, set both iNodo and fNodo to the new node
        iNodo = fNodo = nuevoNodo;
    } else {
        // If the list is not empty, add the new node at the end and update fNodo
        fNodo.siguiente = nuevoNodo;
        fNodo = nuevoNodo;
    }
}
    

    public String generarTabla (){
        StringBuilder tablaHTML = new StringBuilder();
        Nodo actual = iNodo; 
        
        if (actual != null) {
            //Se recorre la lista
            while (actual != null) {
                Libro libro = actual.libro;
                 tablaHTML.append("<tr>");
                tablaHTML.append("<td>").append(libro.getTitulo()).append("</td>");
                tablaHTML.append("<td>").append(libro.getAutor()).append("</td>");
                tablaHTML.append("<td>").append(libro.getAnio()).append("</td>");
                tablaHTML.append("<td>").append(libro.getPortada()).append("</td>");



                tablaHTML.append("</tr>");
                actual = actual.siguiente;
        }
    }

            tablaHTML.append("</table>");  // Cierra la tabla

        return tablaHTML.toString();//Se manda la tabla creada
    }
    }