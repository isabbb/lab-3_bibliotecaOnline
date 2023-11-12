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

    private Nodo iNodo, fNodo;


    public Lista (){
        iNodo = null;
        fNodo = null;
    }

 
    public boolean verificar (){
       return iNodo !=null;
    }

    public void agregar (Libro libros){
           if (iNodo == null){
               iNodo = new Nodo(libros);
           }else {
               iNodo = fNodo = new Nodo(libros);
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